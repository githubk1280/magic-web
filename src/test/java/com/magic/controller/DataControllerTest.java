package com.magic.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.magic.base.AbstractBaseTestConfig;
import com.magic.dao.Kuai3Dao;

public class DataControllerTest extends AbstractBaseTestConfig {
	@Autowired
	private Kuai3Dao dao;

	@Test
	public void queryTermDatas() {
		System.out.println(dao.queryTermDatas("2014-11-20", "2014-11-21"));
	}

	@Test
	public void queryPercentageDatas() {
		System.out.println(dao.queryPercentageDatas(3, "2014-11-20",
				"2014-11-25"));
	}
}
