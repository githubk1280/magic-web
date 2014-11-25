package com.magic.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.magic.base.AbstractBaseTestConfig;
import com.magic.dao.CaiPiaoDao;

public class DataControllerTest extends AbstractBaseTestConfig {
	@Autowired
	private CaiPiaoDao dao;

	@Test
	public void test() {
		System.out.println(dao.queryTermDatas("2014-11-20", "2014-11-21"));
	}
}
