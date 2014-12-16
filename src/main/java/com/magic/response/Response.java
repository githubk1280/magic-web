package com.magic.response;

import lombok.Data;

@Data
public class Response {
	private int status;
	private String msg;

	public Response(int status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}

}
