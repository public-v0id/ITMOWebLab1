package ru.se.ifmo.web.lab1;

import ru.se.ifmo.web.lab1.classes.*;
import ru.se.ifmo.web.lab1.cores.*;
import ru.se.ifmo.web.lab1.queries.*;
import com.fastcgi.*;
import java.nio.charset.StandardCharsets;
import java.io.*;
import java.nio.*;
import java.util.logging.*;
import java.time.Instant;

public class Main {
	public static void main(String[] args) {
		Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		FCGIInterface fcgiInterface = new FCGIInterface();
		log.log(Level.INFO, "Started");
		while (fcgiInterface.FCGIaccept() >= 0) {
			Parser.StringtoQuery(FCGIInterface.request.params.getProperty("REQUEST_METHOD")).execute();
		}
	}
}
