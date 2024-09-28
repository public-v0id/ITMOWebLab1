package ru.se.ifmo.web.lab1.cores;

import java.time.Instant;
import java.nio.charset.StandardCharsets;
import java.io.*;
import java.nio.*;
import ru.se.ifmo.web.lab1.classes.*;
import java.util.logging.*;

public class Parser {
	private static final String HTTP_SUCCESS = """
		Content-Type: application/json
		Content-Length: %d
		
		%s

		""";
	private static final String HTTP_ERROR = """
		HTTP/1.1 400 Bad Request
		Content-Type: application/json
		Content-Length: %d

		%s
		""";
	private static final String RESULT_JSON = """
		{
			"res": %b,
			"exTime": %s,
			"servTime": %s
		}
		""";
	private static final String JSON_ERROR = """
		{
			"reason": "%s"
		}
		""";

	public static Parameters JSONtoParams(String query, Logger logger) {
		String s = query.replace("{", "").replace("}", "");
		if (s == null) {
			return new Parameters(0, 0, 0, false);
		}
		String[] pair = s.split(",");
		double x = 0;
		double y = 0;
		double r = 0;
		boolean xSet = false;
		boolean ySet = false;
		boolean rSet = false;
		for (String str: pair) {
			String[] cur = str.split(":");
			if (cur.length >= 2) {
				cur[0] = cur[0].replaceAll("\"", "");
				switch(cur[0]) {
					case "x":
						{
							String val = cur[1].replaceAll("\"", "");
							if (val.length() > 10) {
								x = (val.charAt(0) == '-' ? -1 : 1) * 1e10;
							}
							x = Double.parseDouble(val);
							xSet = true;
						}
						break;
					case "y":
						{
							String val = cur[1].replaceAll("\"", "");
							y = Double.parseDouble(val);
							ySet = true;
						}
						break;
					case "r":
						{
							String val = cur[1].replaceAll("\"", "");
							r = Double.parseDouble(val);
							rSet = true;
						}
						break;
				}
			}
		}
		logger.log(Level.INFO, Double.toString(x) + " " + Double.toString(y) + " " + Double.toString(r));
		return new Parameters(x, y, r, xSet && ySet && rSet);
	}

	public static String genJSONresp(boolean res, double applTime, Logger logger) {
		try {
			logger.log(Level.INFO, "Started generating");
			String json = String.format(RESULT_JSON, res, "\"" + Double.toString(applTime) + "\"", "\"" + Instant.now().toString() + "\"");
			logger.log(Level.INFO, json);
			json = json.trim();
			return String.format(HTTP_SUCCESS, json.getBytes(StandardCharsets.UTF_8).length, json);
		}
		catch (Exception e) {
			logger.log(Level.WARNING, e.getMessage());
			return "";
		}
	}

	public static QueryEnum StringtoQuery(String query) {
		switch (query) {
			case "POST":
				return QueryEnum.POST;
			default:
				return QueryEnum.NOTPOST;
		}
	}

	public static int StringtoInt(String query) {
		switch (query) {
			case "POST":
				return 0;
			default:
				return 1;
		}
	}
}
