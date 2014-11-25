package com.magic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magic.dao.CaiPiaoDao;
import com.magic.domain.TermData;

@Service
public class DataService {
	@Autowired
	private CaiPiaoDao caiPiaoDao;

	public List<TermData> queryTermDatas(String startDate, String endDate) {
		return caiPiaoDao.queryTermDatas(startDate, endDate);
	}

}
