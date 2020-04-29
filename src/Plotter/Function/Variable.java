package Plotter.Function;

import java.util.Map;

public class Variable extends Function {
	
	private String ID;
	
	public Variable(String ID) {
		this.ID = ID;
	}
	
	@Override
	public double evaluate(Map<String, Double> assignments) {
		if (!assignments.containsKey(ID)) {
			throw new UnsupportedOperationException();
		}
		return assignments.get(ID);
	}
	
	@Override
	public String toString() {
		return ID;
	}
}
