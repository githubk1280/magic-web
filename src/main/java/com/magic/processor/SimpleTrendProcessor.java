package com.magic.processor;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

public class SimpleTrendProcessor {
	public void compute(int target, String date) throws Exception {
		List<Integer> sums = MysqlLocalDao.getSumsByDateOrder(date);
		if (CollectionUtils.isEmpty(sums))
			return;
		int count = 0;
		int up = 0, down = 0, equals = 0;
		for (int i = 0; i < sums.size(); i++) {
			int v = sums.get(i);
			if (v == target) {
				count++;
				if ((i + 1) != sums.size()) {
					int behind = sums.get(i + 1);
					if (behind > target) {
						up++;
					} else if (behind < target) {
						down++;
					} else {
						equals++;
					}
				}
			}
		}
		System.out.print(String.format(
				"target=%d,count=%d,up=%d,down=%d,equeals=%d", target, count,
				up, down, equals));
		MysqlLocalDao.insertKuai3BackTrend(target, count, up, down, equals,
				date);
	}
}
