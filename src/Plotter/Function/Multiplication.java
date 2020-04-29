package Plotter.Function;

import java.util.Map;

public class Multiplication extends BinaryOperation {
	
	public Multiplication(Function left, Function right) {
		super(left, right);
	}
	
	@Override
	public double evaluate(Map<String, Double> assignments) {
		return (left.evaluate(assignments) * right.evaluate(assignments));
	}
	
	@Override
	public String toString() {
		return "(" + left.toString() + " * " + right.toString() + ")";
	}
	
}
