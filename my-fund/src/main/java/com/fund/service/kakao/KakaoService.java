package com.fund.service.kakao;

import com.fund.domain.kakao.KakaoToken;

public interface KakaoService {

	KakaoToken createKakaoToken(String code);
	
	KakaoToken saveKakaoToken(KakaoToken kakaoToken);
	
	void sendMessage(KakaoToken kakaoToken);
	
	KakaoToken getLastKakaoToken();

	void getAccessToken(KakaoToken kakaoToken);
	
	void logout(KakaoToken kakaoToken);
	
	void unlink(KakaoToken kakaoToken);

}
