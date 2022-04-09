package jempasam.mexpression.tree.binary;

import java.util.Map;
import java.util.function.DoubleBinaryOperator;

import jempasam.mexpression.tree.MExpression;

public class BinaryOperatorMExpression extends BinaryMExpression {
	
	private DoubleBinaryOperator operator;

	public BinaryOperatorMExpression(DoubleBinaryOperator operator, MExpression a, MExpression b) {
		super(a, b, "+");
		this.operator=operator;
	}
	
	@Override
	public double get(Map<String, Double> parameters) {
		return operator.applyAsDouble(a.get(parameters),b.get(parameters));
	}
	
}
