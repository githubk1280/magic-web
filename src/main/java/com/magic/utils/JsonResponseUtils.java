package com.magic.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JsonResponseUtils {

	/**
	 * Format and output json string to jsp
	 * 
	 * @param response
	 * @param t
	 * @throws IOException
	 */
	public static <T> void returnJsonResponse(HttpServletResponse response,
			T t, boolean success, int statusCode) throws IOException {
		JSONObject json = new JSONObject();
		String jsonStr = JSON.toJSONString(t);
		json.put("success", success);
		json.put("statusCode", statusCode);
		json.put("data", jsonStr);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.write(json.toJSONString());
		writer.flush();
		writer.close();

	}

}
