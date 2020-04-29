package Plotter.Function;

import java.util.Map;

public class Number extends Function {
	
	private double value;
	
	public Number(double value) {
		this.value = value;
	}
	
	@Override
	public double evaluate(Map<String, Double> assignments) {
		return value;
	}
	
	@Override
	public String toString() {
		return Double.toString(value);
	}
	
}
