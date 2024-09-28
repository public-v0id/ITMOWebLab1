package ru.se.ifmo.web.lab1.classes;

import java.util.logging.*;
import ru.se.ifmo.web.lab1.cores.*;

public class Parameters {
	private double x;
	private double y;
	private double r;
	private boolean valid;

	public Parameters(double x, double y, double r, boolean valid) {
		this.x = x;
		this.y = y;
		this.r = r;
		this.valid = valid;
	}

	public boolean isValid() {
		return valid;
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
