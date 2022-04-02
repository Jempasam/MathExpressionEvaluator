package equation.tree.binary;

import java.util.Map;

import equation.tree.Equation;

public class AdditionEquation extends BinaryEquation {

	public AdditionEquation(Equation a, Equation b) {
		super(a, b, "+");
	}
	
	@Override
	public double get(Map<String, Double> parameters) {
		return a.get(parameters)+b.get(parameters);
	}
	
}
