package Plotter.Function;

import java.util.Map;

public class Exponentiation extends BinaryOperation {

	public Exponentiation(Function left, Function right) {
		super(left, right);
	}

	@Override
	public double evaluate(Map<String, Double> assignments) {
		return Math.pow(left.evaluate(assignments), right.evaluate(assignments));
	}

	@Override
	public String toString() {
		return "(" + left.toString() + ")^(" + right.toString() + ")";
	}
}
