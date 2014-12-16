package com.magic.response;

import java.util.Date;

import lombok.Data;

@Data
public class ColumnResponse {
	private int count;
	private Date hitTime;

	public ColumnResponse(int count, Date hitTime) {
		super();
		this.count = count;
		this.hitTime = hitTime;
	}

	public ColumnResponse() {
	}

}
