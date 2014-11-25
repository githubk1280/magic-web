package com.magic.processor;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

public class TermRowProcessor {
	public void compute(int target, String date) throws Exception {
		List<Integer> sums = MysqlLocalDao.getSumsByDateOrder(date);
		if (CollectionUtils.isEmpty(sums))
			return;
		MysqlLocalDao.insertTermData(sums, date);
	}

}
