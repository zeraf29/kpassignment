package com.kakao.pay.ecotourism.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kakao.pay.ecotourism.entity.EcoTourism;
import com.kakao.pay.ecotourism.entity.EcoTourismId;
import com.kakao.pay.ecotourism.repository.EcoTourismRepository;;

@Service("ecoTourismService")
public class EcoTourismServiceImpl implements EcoTourismService {

	@Autowired
	private EcoTourismRepository ecoTourismRepository;
	
	@Override
	public ArrayList<EcoTourism> findByRegionLike(String region) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<EcoTourism> listEcoTourism = new ArrayList<EcoTourism>();
		listEcoTourism = ecoTourismRepository.findByRegionLike("%"+region+"%");
	
		return listEcoTourism;
	}
	
	@Override
	public ArrayList<EcoTourism> findByIntroduceLike(String introduce) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<EcoTourism> listEcoTourism = new ArrayList<EcoTourism>();
		listEcoTourism = ecoTourismRepository.findByIntroduceLike("%"+introduce+"%");
	
		return listEcoTourism;
	}

	@Override
	public boolean insertEcoTourismDataFromCSV(ArrayList<String[]> data) throws Exception {
		// TODO Auto-generated method stub
		EcoTourism ecoTourism = null;
		EcoTourismId ecoTourismId = null;
		ArrayList<EcoTourism> ListEcoTourism = new ArrayList<EcoTourism>();
		int cnt=0;
		for(String[] temp : data) {
			if(cnt==0) {
				cnt++;
				continue;
			}
			ecoTourism = new EcoTourism();
			
			ecoTourism.setIdx(Long.parseLong(temp[0]));
			ecoTourism.setCode(temp[3]);
			ecoTourism.setPrgm_name(temp[1]);
			ecoTourism.setTheme(temp[2]);
			ecoTourism.setRegion(temp[4]);
			ecoTourism.setIntroduce(temp[5]);
			ecoTourism.setDetail(temp[6]);
			ListEcoTourism.add(ecoTourism);
		}
		ecoTourismRepository.saveAll(ListEcoTourism);
		return true;
	}

	@Override
	public int findByDetailLike(String detail) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<EcoTourism> listEcoTourism = new ArrayList<EcoTourism>();
		listEcoTourism = ecoTourismRepository.findByDetailLike("%"+detail+"%");
		
		int count = 0;
		Pattern p = Pattern.compile(detail);
		String dbSpecific = null;
		for(EcoTourism ecoTourism : listEcoTourism) {
			dbSpecific = ecoTourism.getDetail();
			Matcher m = p.matcher(dbSpecific);
			for(int i=0 ; m.find(i); i=m.end())
				count++;
		}
		return count;
	}
	
	
	@Override
	public ArrayList<EcoTourism> findByRegCode(String reg_code) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<EcoTourism> listEcoTourism = new ArrayList<EcoTourism>();
		listEcoTourism = ecoTourismRepository.findByCode(reg_code);
		
		return listEcoTourism;
	}
	
	@Override
	public EcoTourism saveEcoTourism(EcoTourism ecoTourism) throws Exception {
		boolean result = false;
		
		
		return ecoTourismRepository.save(ecoTourism);
	}
	
	@Override
	public EcoTourism updateEcoTourism(EcoTourism ecoTourism) throws Exception {
		boolean result = false;
		EcoTourism targetEcoTourism = ecoTourismRepository.findByIdxAndCode(ecoTourism.getIdx(), ecoTourism.getCode());
		targetEcoTourism.setPrgm_name(ecoTourism.getPrgm_name());
		targetEcoTourism.setCode(ecoTourism.getCode());
		targetEcoTourism.setTheme(ecoTourism.getTheme());
		targetEcoTourism.setRegion(ecoTourism.getRegion());
		targetEcoTourism.setIntroduce(ecoTourism.getIntroduce());
		targetEcoTourism.setDetail(ecoTourism.getDetail());
		return ecoTourismRepository.save(targetEcoTourism);
	}
	
	@Override
	public EcoTourism findByRegionAndThemeOrIntroduceOrDetail(String region, String keyword) throws Exception {
		
		ArrayList<EcoTourism> listEcoTourism = ecoTourismRepository.findByRegionAndThemeOrIntroduceOrDetail("%"+region+"%", "%"+keyword+"%");
		
		int maxCnt = 0;
		int nowCnt;
		EcoTourism returnEcoTourism = new EcoTourism();
		Matcher m = null;
		Pattern p = Pattern.compile(keyword);
		String dbSpecific = null;
		
		for(EcoTourism ecoTourism : listEcoTourism) {
			nowCnt = 0;
			dbSpecific = ecoTourism.getDetail();
			m = p.matcher(dbSpecific);
			for(int i=0 ; m.find(i); i=m.end())
				nowCnt++;
			dbSpecific = ecoTourism.getIntroduce();
			m = p.matcher(dbSpecific);
			for(int i=0 ; m.find(i); i=m.end())
				nowCnt++;
			dbSpecific = ecoTourism.getTheme();
			m = p.matcher(dbSpecific);
			for(int i=0 ; m.find(i); i=m.end())
				nowCnt++;
			if(nowCnt>=maxCnt) {
				maxCnt = nowCnt;
				returnEcoTourism = ecoTourism;
			}
		}
		
		return returnEcoTourism;
	}

}
