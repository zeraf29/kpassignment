package com.kakao.pay.ecotourism.web;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.persistence.Column;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kakao.pay.ecotourism.entity.EcoTourism;
import com.kakao.pay.ecotourism.service.EcoTourismService;
import com.opencsv.CSVReader;

@RestController
public class EcoTourismApiController {


	@Autowired
	private EcoTourismService ecoTourismService;
	

	//local csv data file location
	private static String csvFileName = "/src/main/resources/static/2019경력공채_개발_사전과제2_2017년_국립공원_생태관광_정보.csv";
	
	@RequestMapping(value = "/api/insert/csv", method = RequestMethod.GET)
	public @ResponseBody boolean insertCsv() throws Exception {
		boolean result = false;
		ClassPathResource cpr = new ClassPathResource(csvFileName);
		ArrayList<String[]> data = new ArrayList<String[]>();
		
		//한글 깨짐(맥북) 아닐 경우 
		CSVReader reader = new CSVReader(new FileReader(cpr.getPath()));
		//한글 깨짐으로 EUC-KR 설정
		//CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(cpr.getPath()), "EUC-KR"));
		String[] s;
        while ((s = reader.readNext()) != null) {
        	data.add(s);
        }
        result = ecoTourismService.insertEcoTourismDataFromCSV(data);
        
        //System.out.println(data);
		return result;
    }
	
	@RequestMapping(value = "/api/search/region/{region}", method = RequestMethod.GET)
	public @ResponseBody JSONObject getDataByRegionLike(@PathVariable("region") String region) throws Exception{
		ArrayList<EcoTourism> ecoTourismList = ecoTourismService.findByRegionLike(region);
		
		JSONObject obj = new JSONObject();
		JSONObject obj2 = new JSONObject();
		JSONArray arr = new JSONArray();
		
		//지역코드 추출
		//중복 제거 처리
		ArrayList<String> regionCode = new ArrayList<String>();
		for(EcoTourism ecoTourism : ecoTourismList) {
			if(!regionCode.contains(ecoTourism.getCode())) {
				regionCode.add(ecoTourism.getCode());
			}
			obj2.put("prgm_name",ecoTourism.getPrgm_name());
			obj2.put("theme",ecoTourism.getTheme());
			arr.add(obj2);
		}
		obj2.put("region", regionCode);
		obj.put("programs", arr);
		return obj;
	}
	
	
	@RequestMapping(value = "/api/search/introduce/{introduce}", method = RequestMethod.GET)
	public @ResponseBody JSONObject getDataByIntroduceLike(@PathVariable("introduce") String introduce) throws Exception{
		ArrayList<EcoTourism> ecoTourismList = ecoTourismService.findByIntroduceLike(introduce);
		
		JSONObject obj = new JSONObject();
		JSONObject obj2 = new JSONObject();
		JSONArray arr = new JSONArray();
		
		//지역정보 추출
		//지역명은 중복 제거 처리(예:샘플 예제의 "세계문화유산"은 "경상북도 경주시"가 2개 이지만 1개만 지역명 표시
		ArrayList<String> regions = new ArrayList<String>();
		for(EcoTourism ecoTourism : ecoTourismList) {
			if(!regions.contains(ecoTourism.getRegion())) {
				regions.add(ecoTourism.getRegion());
			}
		}
		obj2.put("region", regions);
		obj2.put("count", ecoTourismList.size()); //검색 결과수 = 서비스 지역 개
		arr.add(obj2);
		obj.put("programs", arr);
		obj.put("keyword", introduce);
		return obj;
	}
	
	@RequestMapping(value = "/api/count/detail/{detail}", method = RequestMethod.GET)
	public @ResponseBody JSONObject getCountByDetailLike(@PathVariable("detail") String detail) throws Exception{
		int count = ecoTourismService.findByDetailLike(detail);
		//regex 검색 결과수
		
		JSONObject obj = new JSONObject();
		
		obj.put("keyword", detail);
		obj.put("count", count); 
		return obj;
	}
	
	
	@RequestMapping(value = "/api/search/regcode/{reg_code}", method = RequestMethod.GET)
	public @ResponseBody JSONObject getDataByregCode(@PathVariable("reg_code") String reg_code) throws Exception{
		ArrayList<EcoTourism> ecoTourismList = ecoTourismService.findByRegCode(reg_code);
		
		JSONObject obj = new JSONObject();
		JSONObject obj2 = new JSONObject();
		JSONArray arr = new JSONArray();
		
		obj.put("keyword", reg_code);
		for(EcoTourism ecoTourism : ecoTourismList) {
			obj2.put("idx",ecoTourism.getIdx());
			obj2.put("reg_code",ecoTourism.getCode());
			obj2.put("prgm_name",ecoTourism.getPrgm_name());
			obj2.put("theme",ecoTourism.getTheme());
			obj2.put("region",ecoTourism.getRegion());
			obj2.put("introduce",ecoTourism.getIntroduce());
			obj2.put("detail",ecoTourism.getDetail());
			arr.add(obj2);
		}
		obj.put("programs", arr);
		
		return obj;
	}
	
	@RequestMapping(value = "/api/insert/record", method = RequestMethod.POST)
	public @ResponseBody EcoTourism insertEcoTourism(
			@RequestParam(value = "prgm_name", required=true) String prgm_name
			,@RequestParam(value = "code", required=true) String code
			,@RequestParam(value = "theme", required=false) String theme
			,@RequestParam(value = "region", required=true) String region
			,@RequestParam(value = "introduce", required=false) String introduce
			,@RequestParam(value = "detail", required=false) String detail
			) throws Exception{
		
		EcoTourism ecoTourism = new EcoTourism();
		ecoTourism.setPrgm_name(prgm_name);
		ecoTourism.setCode(code);
		ecoTourism.setTheme(theme);
		ecoTourism.setRegion(region);
		ecoTourism.setIntroduce(introduce);
		ecoTourism.setDetail(detail);
		
		return ecoTourismService.saveEcoTourism(ecoTourism);
	}
	
	@RequestMapping(value = "/api/update/record", method = RequestMethod.POST)
	public @ResponseBody EcoTourism updateEcoTourism(
			@RequestParam(value = "idx", required=true) Long idx
			,@RequestParam(value = "prgm_name", required=true) String prgm_name
			,@RequestParam(value = "code", required=true) String code
			,@RequestParam(value = "theme", required=true) String theme
			,@RequestParam(value = "region", required=true) String region
			,@RequestParam(value = "introduce", required=true) String introduce
			,@RequestParam(value = "detail", required=true) String detail
			) throws Exception{
		
		EcoTourism ecoTourism = new EcoTourism();
		ecoTourism.setIdx(idx);
		ecoTourism.setPrgm_name(prgm_name);
		ecoTourism.setCode(code);
		ecoTourism.setTheme(theme);
		ecoTourism.setRegion(region);
		ecoTourism.setIntroduce(introduce);
		ecoTourism.setDetail(detail);
		
		return ecoTourismService.saveEcoTourism(ecoTourism);
	}
	
}
