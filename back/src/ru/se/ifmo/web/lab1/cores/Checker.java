package ru.se.ifmo.web.lab1.cores;

import ru.se.ifmo.web.lab1.classes.*;
import java.util.logging.*;

public class Checker {
	public static boolean check(Parameters params, Logger logger) {
		try {
			return params.isValid() && (checkRect(params) || checkTriangle(params) || checkCircle(params));
		}
		catch (Exception e) {
			logger.log(Level.WARNING, e.getMessage());
			return false;
		}
	}

	private static boolean checkRect(Parameters params) {
		return (params.getY() <= params.getR()/2.0) && (params.getY() >= 0) && (params.getX() >= 0) && (params.getX() <= params.getR());
	}

	private static boolean checkTriangle(Parameters params) {
		return (params.getY() <= 0) && (params.getY() >= (-1)*params.getR()) && (params.getX() >= 0) && (params.getX() <= params.getR()/2.0) && (params.getY() >= 2*params.getX()-params.getR());
	}

	private static boolean checkCircle(Parameters params) {
		return (params.getY() <= 0) && (params.getY() >= (-1)*params.getR()) && (params.getX() <= 0) && (params.getX() >= (-1)*params.getR()) && (params.getR()*params.getR() >= params.getY()*params.getY()+params.getX()*params.getX());
	}
}
