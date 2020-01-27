package com.fund.service.kakao;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.tomcat.util.json.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fund.controller.kakao.KakaoController;
import com.fund.domain.kakao.KakaoToken;
import com.fund.exception.CustomException;
import com.fund.exception.ExceptionCode;
import com.fund.repository.kakao.KakaoRepository;
import com.fund.util.JsonUtil;
import com.fund.util.UrlUtil;

@Service("KakaoService")
public class KakaoServiceImpl implements KakaoService{
	
	private static final Logger logger = LoggerFactory.getLogger(KakaoServiceImpl.class);
	
	@Autowired
	KakaoRepository kakaoRepository;
	
	@Value("${kakao.api.host}")
	private String host;
	
	@Value("${kakao.api.auth.host}")
	private String authHost;
	
	@Value("${kakao.api.javascript.key}")
	private String apiKey;
	
	
	@Value("${kakao.template.id.news}")
	private String newsTemplate;
	
	
	private static final String getTokenUri = "/oauth/token";
	private static final String sendDefaultMsgUri = "/v2/api/talk/memo/default/send";
	private static final String logout = "/v1/user/logout";
	private static final String unlink = "/v1/user/unlink";
	private static final String grantType = "authorization_code";
	private static final String redirectUri = "http://www.shinhan-myfund.site/v1.0/kakao/token"; // /oauth/authorize 때 사용했던 redirect_uri와 동일해야함.
	
	@Override
	public KakaoToken createKakaoToken(String code) {
		KakaoToken kakaoToken = new KakaoToken();
		kakaoToken.setCode(code);
		return kakaoToken;
	}
	
	@Override
	public KakaoToken saveKakaoToken(KakaoToken kakaoToken) {
		return kakaoRepository.save(kakaoToken);
	}
	
	@Override
	public void getAccessToken(KakaoToken kakaoToken) {
		JSONObject result = null; 
		
		String url = authHost+getTokenUri;
		String parameters = "grant_type="+grantType
				+"&client_id="+apiKey
				+"&redirect_uri="+redirectUri
				+"&code="+kakaoToken.getCode();
		
        UrlUtil urlUtil = new UrlUtil();
		try {
			HttpURLConnection con = urlUtil.sendPost(url, parameters,null);
			String response = urlUtil.getResponseMessage(con);
			logger.info("response ==> " + response);
			result = JsonUtil.toJson(response);
		}  catch (ParseException e) {
			throw new CustomException(ExceptionCode.JSON_PARSED_EXCEPTION);
		} catch (IOException e) {
			throw new CustomException(ExceptionCode.SENDING_REQUEST_FAILED);
		}
        
		kakaoToken.setAccessToken(result.getString("access_token"));
		kakaoToken.setTokenType(result.getString("token_type"));
		kakaoToken.setRefreshToken(result.getString("refresh_token"));
		kakaoToken.setExpiresIn(String.valueOf(result.get("expires_in")));
		kakaoToken.setScope(result.getString("scope"));
		kakaoToken.setRefreshTokenExpiresIn(String.valueOf(result.get("refresh_token_expires_in")));
        kakaoRepository.flush();
	}
	
	@Override
	public KakaoToken getLastKakaoToken() {
		KakaoToken kakaoToken = null;
		List<KakaoToken> kakaoTokens = kakaoRepository.findAll(new Sort(Sort.DEFAULT_DIRECTION.DESC, "id"));
		try {
			kakaoToken = kakaoTokens.get(0);
		} catch (Exception e) {
			throw new CustomException(ExceptionCode.NO_KAKAO_TOKEN_FOUND);
		}
		return kakaoToken;
	}
	
	@Override
	public void sendMessage(KakaoToken kakaoToken) {
		/*
		 * JSONObject result = new JSONObject(); JSONObject params = new JSONObject();
		 * JSONObject content = new JSONObject(); // title, imgUrl, link 필수 JSONObject
		 * link = new JSONObject();
		 * 
		 * link.put("web_url", "http://localhost:8080/v1.0/");
		 * link.put("mobile_web_url", "http://localhost:8080/v1.0/");
		 * 
		 * content.put("title", "디저트 사진"); content.put("link", link);
		 * content.put("image_url",
		 * "http://mud-kage.kakao.co.kr/dn/NTmhS/btqfEUdFAUf/FjKzkZsnoeE4o19klTOVI1/openlink_640x640s.jpg"
		 * );
		 * 
		 * 
		 * JSONArray buttons = new JSONArray(); JSONObject button = new JSONObject();
		 * button.put("title", "자세히 보기"); button.put("description", "아메리카노, 빵, 케익");
		 * button.put("link", link); buttons.put(button);
		 * 
		 * params.put("object_type", "text"); params.put("text", "제발...");
		 * params.put("link", link); // params.put("buttons", buttons);
		 */		
		try {
			UrlUtil urlUtil = new UrlUtil();
			String url = host + "/v2/api/talk/memo/send";
			String header = "Bearer " + kakaoToken.getAccessToken();
			String parameter = "template_id=" + newsTemplate;
			HttpURLConnection con = urlUtil.sendPost(url, parameter, header);
			String response = urlUtil.getResponseMessage(con);
			logger.info("response ==> " + response);
			//result = JsonUtil.toJson(response);
			
		} catch (IOException e) {
			throw new CustomException(ExceptionCode.SENDING_REQUEST_FAILED);
		}
		
	}
	
	@Override
	public void logout(KakaoToken kakaoToken) {
		try {
			UrlUtil urlUtil = new UrlUtil();
			String url = host + logout;
			String header = "Bearer " + kakaoToken.getAccessToken();
			HttpURLConnection con = urlUtil.sendPost(url, null, header);
			String response = urlUtil.getResponseMessage(con);
			logger.info("response ==> " + response);
			//result = JsonUtil.toJson(response);
			
		} catch (IOException e) {
			throw new CustomException(ExceptionCode.SENDING_REQUEST_FAILED);
		}
	}
	
	@Override
	public void unlink(KakaoToken kakaoToken) {
		try {
			UrlUtil urlUtil = new UrlUtil();
			String url = host + unlink;
			String header = "Bearer " + kakaoToken.getAccessToken();
			HttpURLConnection con = urlUtil.sendPost(url, null, header);
			String response = urlUtil.getResponseMessage(con);
			logger.info("response ==> " + response);
			//result = JsonUtil.toJson(response);
			
		} catch (IOException e) {
			throw new CustomException(ExceptionCode.SENDING_REQUEST_FAILED);
		}
		
	}
	

}
