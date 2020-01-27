package com.fund.repository.kakao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fund.domain.kakao.KakaoToken;

public interface KakaoRepository extends JpaRepository<KakaoToken, Serializable>{

}
