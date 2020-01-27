package com.fund.controller.kakao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fund.domain.kakao.KakaoToken;
import com.fund.service.kakao.KakaoService;

@Controller
@RequestMapping("/v1.0/kakao")
public class KakaoController {
	
	private static final Logger logger = LoggerFactory.getLogger(KakaoController.class);
	
	@Autowired
	KakaoService kakaoService;
	
	@GetMapping("/code")
	public String kakao() {
		return "kakao/code";
	}
	
	
	@GetMapping("/token")
	public String kakaoToken(@RequestParam String code) {
		logger.info("code ==> " + code);
		
		KakaoToken kakaoToken = kakaoService.createKakaoToken(code);
		kakaoService.saveKakaoToken(kakaoToken);
		kakaoService.getAccessToken(kakaoToken);
		
		return "kakao/token";
	}	
	
	@GetMapping("/message")
	public String kakaoSendMessage() {
		KakaoToken kakaoToken = kakaoService.getLastKakaoToken();
		kakaoService.sendMessage(kakaoToken);
		
		return "kakao/token";
	}
	
	@GetMapping("/logout")
	public String kakaoLogout() {
		KakaoToken kakaoToken = kakaoService.getLastKakaoToken();
		kakaoService.logout(kakaoToken);
		
		return "kakao/token";
	}
	
	@GetMapping("/unlink")
	public String kakaoUnlink() {
		KakaoToken kakaoToken = kakaoService.getLastKakaoToken();
		kakaoService.unlink(kakaoToken);
		
		return "kakao/token";
	}

}
