package com.magic.processor;

import java.util.Calendar;

import com.magic.utils.DateCountDownUtils;

/**
 * @author James markov analysis
 */
public class MarkovAnalysisProcessor {
	public void compute(int[] last, int interval) throws Exception {
		int[][] matrix = new int[16][16];
		//
		Calendar c = Calendar.getInstance();
		String endDate = DateCountDownUtils.format((DateCountDownUtils
				.getTodayDate().getTime()));
		String startDate = DateCountDownUtils.format((DateCountDownUtils
				.getStartDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1
						+ interval, c.get(Calendar.DAY_OF_MONTH)).getTime()));
		for (int i = 0; i < 16; i++) {
			matrix[i] = MysqlLocalDao.getPercentageSumsByDateOrder(i + 3,
					startDate, endDate);
		}
		System.out.println(matrix);
		int result[] = mutiply(last, matrix);
		System.out.println(result);
	}

	private int[] mutiply(int[] last, int[][] matrix) {
		return null;
	}

	public static void main(String args[]) throws Exception {
		MarkovAnalysisProcessor p = new MarkovAnalysisProcessor();
		p.compute(new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
				30);
	}
}
