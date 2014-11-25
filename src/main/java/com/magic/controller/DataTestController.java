package com.magic.controller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.magic.domain.Composite;
import com.magic.domain.TermData;
import com.magic.service.DataService;
import com.magic.utils.DateCountDownUtils;
import com.magic.utils.JsonResponseUtils;

@Controller
public class DataTestController {
	Logger logger = Logger.getLogger(getClass());

	@Autowired
	private DataService service;

	@RequestMapping(value = "/show3")
	public void showFirstThreeDays(HttpServletResponse response)
			throws IOException, IllegalArgumentException, SecurityException,
			IllegalAccessException, NoSuchFieldException {
		List<Composite> result = Lists.newArrayList();
		String endDate = DateCountDownUtils.format(DateCountDownUtils
				.getTodayDate().getTime());
		String startDate = DateCountDownUtils.format(DateCountDownUtils.getDate(
				-7).getTime());
		List<TermData> termDatas = service.queryTermDatas(startDate, endDate);
		for (TermData t : termDatas) {
			result.add(transform(t));
		}
		JsonResponseUtils.returnJsonResponse(response,
				JSON.toJSONString(result), true, 200);
	}

	private Composite transform(TermData t) throws IllegalArgumentException,
			SecurityException, IllegalAccessException, NoSuchFieldException {
		Composite c = new Composite();
		c.setName(DateCountDownUtils.format(t.getHistoryDate()));
		int[] data = new int[81];
		for (int i = 0; i < 81; i++) {
			if (i == 0) {
				data[i] = 0;
			} else {
				Field f = TermData.class.getDeclaredField("term" + i);
				f.setAccessible(true);
				data[i] = f.getInt(t);
			}
		}
		c.setData(data);
		return c;
	}
}
