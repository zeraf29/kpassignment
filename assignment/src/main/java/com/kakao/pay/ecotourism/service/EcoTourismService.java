package com.kakao.pay.ecotourism.service;

import java.util.ArrayList;
import java.util.List;

import com.kakao.pay.ecotourism.entity.EcoTourism;

public interface EcoTourismService {
	ArrayList<EcoTourism> findByIntroduceLike(String introduce) throws Exception;
	ArrayList<EcoTourism> findByRegionLike(String region) throws Exception;
	int findByDetailLike(String specific) throws Exception;
	boolean insertEcoTourismDataFromCSV(ArrayList<String[]> data) throws Exception;
	ArrayList<EcoTourism> findByRegCode(String reg_code) throws Exception;
	EcoTourism saveEcoTourism(EcoTourism ecoTourism) throws Exception;
	EcoTourism updateEcoTourism(EcoTourism ecoTourism) throws Exception;
	EcoTourism findByRegionAndThemeOrIntroduceOrDetail(String region, String keyword) throws Exception;
}
