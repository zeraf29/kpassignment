package com.kakao.pay.ecotourism.web;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		
		//한글 깨짐으로 EUC-KR 설정
		CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(cpr.getPath()), "EUC-KR"));
		String[] s;
        while ((s = reader.readNext()) != null) {
        	data.add(s);
        }
        result = ecoTourismService.insertEcoTourismDataFromCSV(data);
        
        //System.out.println(data);
		return result;
    }
	
	@RequestMapping(value = "/api/data/region/{region}/all", method = RequestMethod.GET)
	public @ResponseBody ArrayList<String[]> getDataAllByRegion(@PathVariable("region") String region){
		ArrayList<String[]> data = null;
		
		
		
		return data;
	}
	
}
