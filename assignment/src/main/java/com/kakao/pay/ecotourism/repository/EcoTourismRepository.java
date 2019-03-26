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
	ArrayList<EcoTourism> findByCode(String reg_code);
	EcoTourism findByIdxAndCode(long idx, String reg_code);
	
	@Query(value = "SELECT idx, code, detail, introduce, prgm_name, region, theme FROM EcoTourism e WHERE region LIKE :region and (theme LIKE :keyword OR introduce LIKE :keyword OR detail LIKE :keyword)")
	ArrayList<EcoTourism> findByRegionAndThemeOrIntroduceOrDetail(@Param("region") String region, @Param("keyword") String keyword);
}
