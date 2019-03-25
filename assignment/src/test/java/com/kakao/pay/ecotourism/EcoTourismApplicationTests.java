package com.kakao.pay.ecotourism;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kakao.pay.ecotourism.entity.EcoTourism;
import com.kakao.pay.ecotourism.entity.EcoTourismId;
import com.kakao.pay.ecotourism.repository.EcoTourismRepository;
import com.kakao.pay.ecotourism.service.EcoTourismService;
import com.kakao.pay.ecotourism.service.EcoTourismServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EcoTourismServiceImpl.class, EcoTourismRepository.class, EcoTourism.class, EcoTourismId.class})
@SpringBootApplication(scanBasePackages={
"com.kakao.pay.ecotourism.entity", "com.kakao.pay.ecotourism.repository", "com.kakao.pay.ecotourism.service"})
public class EcoTourismApplicationTests {

	@Autowired
	private EcoTourismService ecoTourismService;
	
	@Test
	public void testGetDataByRegionLike() throws Exception {
		String region = "평창군";
		ArrayList<EcoTourism> ecoTourismList = ecoTourismService.findByRegionLike(region);
		assertThat(ecoTourismList.size(), is(4));
	}
	
	@Test
	public void testGetDataByIntroduceLike() throws Exception {
		String introduce = "유적지";
		ArrayList<EcoTourism> ecoTourismList = ecoTourismService.findByIntroduceLike(introduce);
		assertThat(ecoTourismList.size(), is(4));
	}
	
	@Test
	public void testGetDataByregCode() throws Exception {
		String reg_code = "reg0001";
		ArrayList<EcoTourism> ecoTourismList = ecoTourismService.findByRegCode(reg_code);
		assertThat(ecoTourismList.size(), is(1));
	}
	
	@Test
	public void testGetCountByDetailLike() throws Exception {
		String detail = "문화";
		int count = ecoTourismService.findByDetailLike(detail);
		assertThat(count, is(47));
	}

}
