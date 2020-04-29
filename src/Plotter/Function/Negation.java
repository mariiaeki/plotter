package Plotter.Function;

import java.util.Map;

public class Negation extends UnaryOperation {
	
	public Negation(Function operator) {
		super(operator);
	}
	
	@Override
	public double evaluate(Map<String, Double> assignments) {
		return operator.evaluate(assignments);
	}
	
	@Override
	public String toString() {
		return "( -1 * " + operator.toString()+ ")";
	}
}
