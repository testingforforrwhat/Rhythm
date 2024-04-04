package com.test;

import com.test.bean.po.Advertisements;
import com.test.mapper.AdvertisementsMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationTest {


	@Autowired
	private AdvertisementsMapper advertisementsMapper;



	@Test
	public void selectAdList(){

		Advertisements advertisements = advertisementsMapper.selectByAdId(10);

		System.out.println(advertisements);

	}

}