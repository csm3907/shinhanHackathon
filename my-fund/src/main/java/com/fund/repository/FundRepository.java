package com.fund.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fund.domain.Fund;

public interface FundRepository extends JpaRepository<Fund, Serializable> {

	Optional<Fund> findByScCode(String scCode);

	Fund findByKliaCode(String string);

}
