package jempasam.mexpression.tree.unary;

import java.util.Map;
import java.util.function.DoubleUnaryOperator;

import jempasam.mexpression.tree.MExpression;

public class UnaryOperatorMExpression extends UnaryMExpression {

	private DoubleUnaryOperator operator;
	
	
	public UnaryOperatorMExpression(MExpression a, DoubleUnaryOperator operator, String visual) {
		super(a, visual+"(",")");
		this.operator=operator;
	}
	
	public UnaryOperatorMExpression(MExpression a, DoubleUnaryOperator operator) {
		this(a,operator, operator.getClass().getSimpleName());
	}
	
	@Override
	public double get(Map<String, Double> parameters) {
		return operator.applyAsDouble(a.get(parameters));
	}
	
}