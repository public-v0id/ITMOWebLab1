package ru.se.ifmo.web.lab1.classes;

import java.util.logging.*;

public class Parameters {
	private double x;
	private double y;
	private double r;
	public String query;
	public int lines;
	private Logger logger;

	public Parameters(double x, double y, double r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}

	public Parameters(String s, Logger log) {
		this.logger = log;
		lines = 0;
		query = s;
		y = -100;
		r = -100;
		if (s == null) {
			return;
		}
		String[] pair = s.split(",");
		for (String str: pair) {
			logger.log(Level.INFO, str);
			++lines;
			String[] cur = str.split(":");
			if (cur.length >= 2) {
				cur[0] = cur[0].replaceAll("\"", "");
				logger.log(Level.INFO, cur[0]);
				switch(cur[0]) {
					case "x":
						{
							String val = cur[1].replaceAll("\"", "");
							if (val.length() > 10) {
								this.x = (val.charAt(0) == '-' ? -1 : 1) * 1000;
							}
							logger.log(Level.INFO, "x = " + val);
							this.x = Double.parseDouble(val);
						}
						break;
					case "y":
						{
							String val = cur[1].replaceAll("\"", "");
							logger.log(Level.INFO, "y = " + val);
							this.y = Double.parseDouble(val);
						}
						break;
					case "r":
						{
							String val = cur[1].replaceAll("\"", "");
							logger.log(Level.INFO, "r = " + val);
							this.r = Double.parseDouble(val);
						}
						break;
				}
			}
		}
	}

	public boolean isValid() {
		return (y != -100) || (r != -100);
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

	public boolean check() {
		return checkRect() || checkTriangle() || checkCircle();
	}

	private boolean checkRect() {
		return (y <= r/2.0) && (y >= 0) && (x >= 0) && (x <= r);
	}

	private boolean checkTriangle() {
		return (y <= 0) && (y >= (-1)*r) && (x >= 0) && (x <= r/2.0) && (y >= 2*x-r);
	}

	private boolean checkCircle() {
		return (y <= 0) && (y >= (-1)*r) && (x <= 0) && (x >= (-1)*r) && (r*r >= y*y+x*x);
	}
}
