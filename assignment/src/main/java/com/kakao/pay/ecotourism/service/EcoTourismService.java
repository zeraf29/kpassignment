package com.kakao.pay.ecotourism.service;

import java.util.ArrayList;
import java.util.List;

import com.kakao.pay.ecotourism.entity.EcoTourism;

public interface EcoTourismService {
	List<EcoTourism> findAllByRegionLike(String region) throws Exception;
	boolean insertEcoTourismDataFromCSV(ArrayList<String[]> data) throws Exception;
}
