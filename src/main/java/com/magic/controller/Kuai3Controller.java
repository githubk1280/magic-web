package com.magic.controller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.magic.crawler.Magic163Processor;
import com.magic.domain.BaseKuai3Data;
import com.magic.domain.Composite;
import com.magic.domain.Kuai3Response;
import com.magic.domain.PercentageData;
import com.magic.domain.TermData;
import com.magic.service.Kuai3Service;
import com.magic.utils.DateCountDownUtils;
import com.magic.utils.JsonResponseUtils;

@Controller
public class Kuai3Controller {
	Logger logger = Logger.getLogger(getClass());

	@Autowired
	private Kuai3Service service;

	@RequestMapping(value = "/show3")
	public void showFirstThreeDays(HttpServletResponse response)
			throws IOException, IllegalArgumentException, SecurityException,
			IllegalAccessException, NoSuchFieldException {
		List<Composite> result = Lists.newArrayList();
		String endDate = DateCountDownUtils.format(DateCountDownUtils
				.getTodayDate().getTime());
		String startDate = DateCountDownUtils.format(DateCountDownUtils
				.getDate(-7).getTime());
		List<TermData> termDatas = service.queryTermDatas(startDate, endDate);
		for (TermData t : termDatas) {
			result.add(transform(t));
		}
		JsonResponseUtils.returnJsonResponse(response,
				JSON.toJSONString(result), true, 200);
	}

	@RequestMapping(value = "/showPercentage/{target}")
	public void showPercentage(HttpServletResponse response,
			@PathVariable int target) throws IOException,
			IllegalArgumentException, SecurityException,
			IllegalAccessException, NoSuchFieldException {
		String endDate = DateCountDownUtils.format(DateCountDownUtils
				.getTodayDate().getTime());
		String startDate = DateCountDownUtils.format(DateCountDownUtils
				.getDate(-7).getTime());
		Kuai3Response kResponse = new Kuai3Response();
		List<Composite> result = Lists.newArrayList();
		List<PercentageData> percentageDatas = service.queryPercentageDatas(
				target, startDate, endDate);
		for (PercentageData t : percentageDatas) {
			result.add(transform(t));
		}
		kResponse.setComposites(result);
		kResponse.setTarget(target);
		JsonResponseUtils.returnJsonResponse(response,
				JSON.toJSONString(kResponse), true, 200);
	}

	@RequestMapping(value = "/crawl/{dateStr}")
	public void crawl(@PathVariable String dateStr) throws InterruptedException {
		String[] d = dateStr.split(" ");
		Calendar start = DateCountDownUtils.getStartDate(
				DateCountDownUtils.getInt(d[0]),
				DateCountDownUtils.getInt(d[1]),
				DateCountDownUtils.getInt(d[2]));
		int total = DateCountDownUtils.getInt(d[3]);
		for (int i = 0; i < total; i++) {
			int sleep = new Random().nextInt(5);
			System.out.println("sleep..." + sleep);
			TimeUnit.SECONDS.sleep(sleep);
			dateStr = DateCountDownUtils.countDown(start.get(Calendar.YEAR),
					start.get(Calendar.MONTH),
					start.get(Calendar.DAY_OF_MONTH), i);
			if (StringUtils.isNotEmpty(dateStr)) {
				Spider.create(new Magic163Processor())
						.addPipeline(new ConsolePipeline())
						.addUrl("http://caipiao.163.com/award/kuai3/?gameEn=kuai3&date="
								+ dateStr).thread(1).run();
			}
		}
	}

	private Composite transform(Object t) throws IllegalArgumentException,
			SecurityException, IllegalAccessException, NoSuchFieldException {
		Composite c = new Composite();
		c.setName(DateCountDownUtils.format(((BaseKuai3Data) t)
				.getHistoryDate()));
		int[] data = new int[81];
		for (int i = 0; i < 81; i++) {
			if (i == 0) {
				data[i] = 0;
			} else {
				Field f = null;
				if (t instanceof TermData) {
					f = TermData.class.getDeclaredField("term" + i);
					f.setAccessible(true);
					data[i] = f.getInt(t);
				} else if (t instanceof PercentageData) {
					if (i > 18) {
						break;
					}
					if (i > 2 && i < 19) {
						f = PercentageData.class.getDeclaredField("num" + i);
						f.setAccessible(true);
						data[i] = f.getInt(t) * 100;
					}
				}
			}
		}
		c.setData(data);
		return c;
	}
}
