package Plotter.Function;

import java.util.Map;

public abstract class Function {
	
	/**
	 * Evaluate the function using the given variable assignments.
	 * @param assignments variable assignments
	 * @return function evaluated width given assignment 
	 */
	public abstract double evaluate(Map<String, Double> assignments) throws UnsupportedOperationException;
	
	/**
	 * Print the function in some humanly readable format.
	 * @return readable format of the function
	 */
	public abstract String toString();
	
}
