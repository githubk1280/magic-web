package com.magic.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.magic.domain.PercentageData;
import com.magic.domain.TermData;

@Repository
public interface Kuai3Dao {
	public List<TermData> queryTermDatas(
			@Param(value = "startDate") String startDate,
			@Param(value = "endDate") String endDate);

	public List<PercentageData> queryPercentageDatas(
			@Param(value = "target") int target,
			@Param(value = "startDate") String startDate,
			@Param(value = "endDate") String endDate);
}
