package equation.tree.unary;

import java.util.Map;

import equation.tree.Equation;

public class SquareRootEquation extends UnaryEquation {

	public SquareRootEquation(Equation a) {
		super(a, "sqrt(",")");
	}
	
	@Override
	public double get(Map<String, Double> parameters) {
		return Math.sqrt(a.get(parameters));
	}
	
}
