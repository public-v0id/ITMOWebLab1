package ru.se.ifmo.web.lab1.queries;

import ru.se.ifmo.web.lab1.cores.*;
import ru.se.ifmo.web.lab1.classes.*;
import java.util.logging.*;

public class UnknownQuery implements IQuery{
	@Override
	public void execute() {
		Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		log.log(Level.INFO, "Unknown query");
	}
}
