package com.kakao.pay.ecotourism.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kakao.pay.ecotourism.entity.EcoTourism;
import com.kakao.pay.ecotourism.repository.EcoTourismRepository;;

@Service("ecoTourismService")
public class EcoTourismServiceImpl implements EcoTourismService {

	@Autowired
	private EcoTourismRepository ecoTourismRepository;
	
	@Override
	public List<EcoTourism> findAllByRegionLike(String region) throws Exception {
		// TODO Auto-generated method stub
		
		ArrayList<EcoTourism> ecoTourism = ecoTourismRepository.findByRegionLike(region);
	
		return ecoTourism;
	}

	@Override
	public boolean insertEcoTourismDataFromCSV(ArrayList<String[]> data) throws Exception {
		// TODO Auto-generated method stub
		EcoTourism ecoTourism = null;
		ArrayList<EcoTourism> ListEcoTourism = new ArrayList<EcoTourism>();
		int cnt=0;
		for(String[] temp : data) {
			if(cnt==0) {
				cnt++;
				continue;
			}
			ecoTourism = new EcoTourism();
			ecoTourism.setIdx(Long.parseLong(temp[0]));
			ecoTourism.setPrgm_name(temp[1]);
			ecoTourism.setTheme(temp[2]);
			ecoTourism.setRegion(temp[3]);
			ecoTourism.setIntroduce(temp[4]);
			ecoTourism.setSpecfic(temp[5]);
			ListEcoTourism.add(ecoTourism);
		}
		ecoTourismRepository.saveAll(ListEcoTourism);
		return true;
	}
	
	


}
