package com.magic.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.magic.domain.TermData;

@Repository
public interface CaiPiaoDao {
	public List<TermData> queryTermDatas(
			@Param(value = "startDate") String startDate,
			@Param(value = "endDate") String endDate);
}
