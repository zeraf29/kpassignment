package com.kakao.pay.ecotourism.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kakao.pay.ecotourism.entity.EcoTourism;

public interface EcoTourismRepository extends JpaRepository<EcoTourism, Integer>{
	
	ArrayList<EcoTourism> findByRegionLike(String region);
	ArrayList<EcoTourism> findByIntroduceLike(String introduce);
	ArrayList<EcoTourism> findByDetailLike(String detail);
	
}
