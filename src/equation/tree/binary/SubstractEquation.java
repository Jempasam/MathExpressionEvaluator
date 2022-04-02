package equation.tree.binary;

import java.util.Map;

import equation.tree.Equation;

public class SubstractEquation extends BinaryEquation {

	public SubstractEquation(Equation a, Equation b) {
		super(a, b, "-");
	}
	
	@Override
	public double get(Map<String, Double> parameters) {
		return a.get(parameters)-b.get(parameters);
	}
	
}
