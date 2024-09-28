package ru.se.ifmo.web.lab1.cores;

import ru.se.ifmo.web.lab1.classes.*;
import java.util.logging.*;

public class POSTQuery implements IQuery{
	@Override
	public void execute(Logger log) {
		String queryProps = "";
		long startTime = System.nanoTime();
		try {
			queryProps = Input.readRequestBody();
			log.log(Level.INFO, queryProps);
		}
		catch (Exception e) {
			log.log(Level.WARNING, e.getMessage());
		}
		Parameters params = Parser.JSONtoParams(queryProps, log);
		log.log(Level.INFO, "Formatting response");
		try {	
			String res = Parser.genJSONresp(Checker.check(params, log), (System.nanoTime()-startTime)/1000.0, log);
			log.log(Level.INFO, res);
			System.out.println(res);		
		}
		catch (Exception e) {
			log.log(Level.WARNING, e.getMessage());
		}
	}
}
