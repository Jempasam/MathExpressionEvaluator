package jempasam.mexpression.tree.binary;

import java.util.Map;
import java.util.function.DoubleBinaryOperator;
import jempasam.mexpression.tree.MExpression;

public class BinaryOperatorMExpression extends BinaryMExpression {
	
	private DoubleBinaryOperator operator;
	
	public BinaryOperatorMExpression(MExpression a, MExpression b,DoubleBinaryOperator operator, String visual) {
		super(a, b, visual);
		this.operator=operator;
	}
	
	public BinaryOperatorMExpression(MExpression a, MExpression b, DoubleBinaryOperator operator) {
		this(a, b, operator, " "+operator.getClass().getSimpleName()+" ");
	}
	
	@Override
	public double get(Map<String, Double> parameters) {
		return operator.applyAsDouble(a.get(parameters),b.get(parameters));
	}
	
}
