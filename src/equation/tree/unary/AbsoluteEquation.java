package equation.tree.unary;

import java.util.Map;

import equation.tree.Equation;

public class AbsoluteEquation extends UnaryEquation {

	public AbsoluteEquation(Equation a) {
		super(a, "|","|");
	}
	
	@Override
	public double get(Map<String, Double> parameters) {
		return Math.abs(a.get(parameters));
	}
	
}
