package equation.tree.unary;

import java.util.Map;
import java.util.function.DoubleUnaryOperator;
import equation.tree.Equation;

public class UnaryOperatorEquation extends UnaryEquation {

	private DoubleUnaryOperator operator;
	
	public UnaryOperatorEquation(Equation a, DoubleUnaryOperator operator) {
		super(a, operator.getClass().getSimpleName()+"(",")");
		this.operator=operator;
	}
	
	@Override
	public double get(Map<String, Double> parameters) {
		return operator.applyAsDouble(a.get(parameters));
	}
	
}