package equation.tree.binary;

import java.util.Map;

import equation.tree.Equation;

public class PowerEquation extends BinaryEquation {

	public PowerEquation(Equation a, Equation b) {
		super(a, b, "^");
	}
	
	@Override
	public double get(Map<String, Double> parameters) {
		return Math.pow(a.get(parameters),b.get(parameters));
	}
	
}
