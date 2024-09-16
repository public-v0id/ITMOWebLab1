package ru.se.ifmo.web.lab1;

import ru.se.ifmo.web.lab1.classes.*;
import com.fastcgi.*;
import java.nio.charset.StandardCharsets;
import java.io.*;
import java.nio.*;

public class Main {
	private static final String HTTP_SUCCESS = """
		HTTP/1.1 200 OK
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
			"result": %b
		}
		""";
	private static final String ERROR_JSON = """
		{
			"reason": "%s"
		}
		""";
	public static void main(String[] args) {
		FCGIInterface fcgiInterface = new FCGIInterface();
		while (fcgiInterface.FCGIaccept() >= 0) {
			String queryProps = System.getProperties().getProperty("QUERY_STRING");
			Parameters params = new Parameters(queryProps);
			String json = String.format(RESULT_JSON, params.check());
			json = json.trim();
			String  resp = String.format(HTTP_SUCCESS, json.getBytes(StandardCharsets.UTF_8).length, json);
			try {
				FCGIInterface.request.outStream.write(resp.getBytes(StandardCharsets.UTF_8));
				FCGIInterface.request.outStream.flush();
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
