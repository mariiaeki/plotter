package Plotter.Function;

import java.util.Map;

public class Division extends BinaryOperation {
	
	public Division(Function left, Function right) {
		super(left, right);
	}
	
	@Override
	public double evaluate(Map<String, Double> assignments) {
		if (right.evaluate(assignments) != 0){
		return (left.evaluate(assignments) / right.evaluate(assignments));} else
			throw new ArithmeticException("Division by zero!");
	}
	
	@Override
	public String toString() {
		return "(" + left.toString() + " / " + right.toString() + ")";
	}
}
