package com.test;

import com.test.bean.po.Advertisements;
import com.test.mapper.AdvertisementsMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ApplicationTest {


	@Autowired
	private AdvertisementsMapper advertisementsMapper;



	@Test
	public void selectAdList(){

		List<Advertisements> advertisementsList = advertisementsMapper.selectListByAdId(10);

		for (Advertisements advertisements : advertisementsList) {
			System.out.println(advertisements);
		}

	}

}