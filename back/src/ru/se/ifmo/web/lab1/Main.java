package ru.se.ifmo.web.lab1;

import ru.se.ifmo.web.lab1.classes.*;
import com.fastcgi.*;
import java.nio.charset.StandardCharsets;
import java.io.*;
import java.nio.*;
import java.util.logging.*;
import java.time.Instant;

public class Main {
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

	private static String readRequestBody() throws IOException {
		FCGIInterface.request.inStream.fill();
		var contentLength = FCGIInterface.request.inStream.available();
		var buffer = ByteBuffer.allocate(contentLength);
		var readBytes = FCGIInterface.request.inStream.read(buffer.array(), 0, contentLength);
		var requestBodyRaw = new byte[readBytes];
		buffer.get(requestBodyRaw);
		buffer.clear();
		return new String(requestBodyRaw, StandardCharsets.UTF_8);
	}

	public static void main(String[] args) {
		Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		FCGIInterface fcgiInterface = new FCGIInterface();
		log.log(Level.INFO, "Started");
		while (fcgiInterface.FCGIaccept() >= 0) {
			String method = FCGIInterface.request.params.getProperty("REQUEST_METHOD");
			if (method.equals("POST")) {
				String queryProps = "";
				long startTime = System.nanoTime();
				try {
					queryProps = readRequestBody();
					log.log(Level.INFO, queryProps);
					queryProps = queryProps.replace("{", "").replace("}", "");
					log.log(Level.INFO, queryProps);
				}
				catch (Exception e) {
					log.log(Level.INFO, e.getMessage());
				}
				Parameters params = new Parameters(queryProps, log);
				log.log(Level.INFO, "Formatting response");
				try {
					String json = String.format(RESULT_JSON, params.check(), "\"" + Double.toString((System.nanoTime()-startTime)/1000.0) + "\"", "\"" + Instant.now().toString() + "\"");
					json = json.trim();
					String  resp = String.format(HTTP_SUCCESS, json.getBytes(StandardCharsets.UTF_8).length, json);
					System.out.println(resp);		
				}
				catch (Exception e) {
					log.log(Level.INFO, e.getMessage());
				}
			}
			else if (method.equals("GET")) {
				log.log(Level.INFO, "GET");
			}
		}
	}
}
