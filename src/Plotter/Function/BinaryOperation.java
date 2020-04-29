package Plotter.Function;

public abstract class BinaryOperation extends Function {
	
	protected Function left;
	protected Function right;
	
	public BinaryOperation(Function left, Function right) {
		this.left = left;
		this.right = right;
	}
	
}
