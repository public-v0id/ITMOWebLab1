package ru.se.ifmo.web.lab1.queries;

import ru.se.ifmo.web.lab1.classes.*;
import ru.se.ifmo.web.lab1.cores.*;
import java.util.logging.*;
import java.io.*;
import ru.se.ifmo.web.lab1.exceptions.*;

public class POSTQuery implements IQuery{
	@Override
	public void execute() {
		Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		String queryProps = "";
		long startTime = System.nanoTime();
		try {
			queryProps = Input.readRequestBody();
		}
		catch (IOException e) {
			log.log(Level.WARNING, e.getMessage());
			return;
		}
		log.log(Level.INFO, queryProps);
		try {
			Parameters params = Parser.JSONtoParams(queryProps);
			log.log(Level.INFO, "Formatting response");
			String res = Parser.genJSONresp(Checker.check(params), (System.nanoTime()-startTime)/1000.0);
			log.log(Level.INFO, res);
			System.out.println(res);		
		}
		catch (JSONConvException e) {
			log.log(Level.WARNING, e.getMessage());
		}
	}
}
