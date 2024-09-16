package ru.se.ifmo.web.lab1.classes;

public class Parameters {
	private double x;
	private double y;
	private double r;

	public Parameters(double x, double y, double r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}

	public Parameters(String s) {
		String[] pair = s.split("&");
		for (String str: pair) {
			String[] cur = str.split("=");
			if (cur.length == 2) {
				switch(cur[0]) {
					case "x":
						this.x = Double.parseDouble(cur[1]);
						break;
					case "y":
						this.y = Double.parseDouble(cur[1]);
						break;
					case "r":
						this.r = Double.parseDouble(cur[1]);
						break;

				}
			}
		}
	}
	
	public boolean check() {
		return checkRect() && checkTriangle() && checkCircle();
	}

	private boolean checkRect() {
		return (y <= r/2.0) && (y >= 0) && (x >= 0) && (x <= r);
	}

	private boolean checkTriangle() {
		return (y <= 0) && (y >= (-1)*r) && (x >= 0) && (x <= r/2.0) && (y >= 2*x-r);
	}

	private boolean checkCircle() {
		return (y <= 0) && (y >= (-1)*r) && (x <= 0) && (x >= (-1)*r) && (r*r <= y*y+x*x);
	}
}
