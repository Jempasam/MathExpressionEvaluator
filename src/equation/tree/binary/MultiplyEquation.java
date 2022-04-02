package equation.tree.binary;

import java.util.Map;

import equation.tree.Equation;

public class MultiplyEquation extends BinaryEquation {

	public MultiplyEquation(Equation a, Equation b) {
		super(a, b, "×");
	}
	
	@Override
	public double get(Map<String, Double> parameters) {
		return a.get(parameters)*b.get(parameters);
	}
	
}
