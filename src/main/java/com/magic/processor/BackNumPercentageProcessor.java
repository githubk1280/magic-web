package com.magic.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.magic.utils.DateCountDownUtils;

/**
 * @author James 后一位百分比
 */
public class BackNumPercentageProcessor {
	public void compute(int target, String date) throws Exception {
		List<Integer> sums = MysqlLocalDao.getSumsByDateOrder(date);
		if (CollectionUtils.isEmpty(sums))
			return;
		// System.out.println(3 + "," + sums);
		int targetHit = sums.contains(target) ? 1 : 0;
		Map<Integer, Integer> behindHitCountMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < sums.size(); i++) {
			if (sums.get(i) == target) {
				if (i + 1 == sums.size())
					continue;
				int behind = sums.get(i + 1);
				int count = behindHitCountMap.get(behind) == null ? 0
						: behindHitCountMap.get(behind);
				behindHitCountMap.put(behind, ++count);
			}
		}
		// System.out.println(target + ">" + behindHitCountMap);
		MysqlLocalDao.insertKuai3BehindNumPercentage(target, targetHit,
				getFromMap(behindHitCountMap, 3),
				getFromMap(behindHitCountMap, 4),
				getFromMap(behindHitCountMap, 5),
				getFromMap(behindHitCountMap, 6),
				getFromMap(behindHitCountMap, 7),
				getFromMap(behindHitCountMap, 8),
				getFromMap(behindHitCountMap, 9),
				getFromMap(behindHitCountMap, 10),
				getFromMap(behindHitCountMap, 11),
				getFromMap(behindHitCountMap, 12),
				getFromMap(behindHitCountMap, 13),
				getFromMap(behindHitCountMap, 14),
				getFromMap(behindHitCountMap, 15),
				getFromMap(behindHitCountMap, 16),
				getFromMap(behindHitCountMap, 17),
				getFromMap(behindHitCountMap, 18), date);
	}

	public int getFromMap(Map<Integer, Integer> map, int key) {
		return map.get(key) == null ? 0 : map.get(key);
	}

	public static void main(String args[]) throws Exception {
		BackNumPercentageProcessor p = new BackNumPercentageProcessor();
		String dStr = DateCountDownUtils.format(DateCountDownUtils.getDate(-1)
				.getTime());
		System.out.println(dStr);
		for (int i = 3; i < 19; i++) {
			p.compute(i, dStr);
		}

	}
}
