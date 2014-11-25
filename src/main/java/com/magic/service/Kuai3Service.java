package com.magic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magic.dao.Kuai3Dao;
import com.magic.domain.PercentageData;
import com.magic.domain.TermData;

@Service
public class Kuai3Service {
	@Autowired
	private Kuai3Dao kuai3Dao;

	public List<TermData> queryTermDatas(String startDate, String endDate) {
		return kuai3Dao.queryTermDatas(startDate, endDate);
	}

	public List<PercentageData> queryPercentageDatas(int target,
			String startDate, String endDate) {
		return kuai3Dao.queryPercentageDatas(target, startDate, endDate);

	}

}
