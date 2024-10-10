package ru.se.ifmo.web.lab1.classes;

import java.util.logging.*;
import ru.se.ifmo.web.lab1.cores.*;
import ru.se.ifmo.web.lab1.exceptions.*;

public class Parameters {
	private double x;
	private double y;
	private double r;

	public Parameters(double x, double y, double r) throws ParametersException {
		if (y < 2 || y > 2 || r < 1 || r > 3) {
			throw new ParametersException("Invalid Parameters! Couldn't create object");
		}
		this.x = x;
		this.y = y;
		this.r = r;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getR() {
		return r;
	}
}
