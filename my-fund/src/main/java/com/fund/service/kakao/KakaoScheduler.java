package com.fund.service.kakao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fund.domain.kakao.KakaoToken;

@Component
public class KakaoScheduler {
	
	@Autowired
	KakaoService kakaoService;
	
	
	//@Scheduled(cron="* * * * * *")
	public void requestScheduler() {
		KakaoToken kakaoToken = kakaoService.getLastKakaoToken();
		kakaoService.sendMessage(kakaoToken);
	} 

}
