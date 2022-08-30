package jempasam.mexpression.tree.doubletype.binary;

import java.util.Map;
import java.util.function.DoubleBinaryOperator;

import jempasam.mexpression.tree.MExpressionParameter;
import jempasam.mexpression.tree.doubletype.DoubleMExpression;

public class BinaryOperatorMExpression extends BinaryDoubleMExpression {
	
	private DoubleBinaryOperator operator;
	
	public BinaryOperatorMExpression(DoubleMExpression a, DoubleMExpression b,DoubleBinaryOperator operator, String visual) {
		super(a, b, visual);
		this.operator=operator;
	}
	
	public BinaryOperatorMExpression(DoubleMExpression a, DoubleMExpression b, DoubleBinaryOperator operator) {
		this(a, b, operator, " "+operator.getClass().getSimpleName()+" ");
	}
	
	@Override
	public double pget(Map<MExpressionParameter<?>, Object> parameters) {
		return operator.applyAsDouble(a.pget(parameters),b.pget(parameters));
	}
	
}
