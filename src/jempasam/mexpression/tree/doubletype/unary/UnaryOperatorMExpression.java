package jempasam.mexpression.tree.doubletype.unary;

import java.util.Map;
import java.util.function.DoubleUnaryOperator;

import jempasam.mexpression.tree.MExpressionParameter;
import jempasam.mexpression.tree.doubletype.DoubleMExpression;

public class UnaryOperatorMExpression extends UnaryDoubleMExpression {

	private DoubleUnaryOperator operator;
	
	
	public UnaryOperatorMExpression(DoubleMExpression a, DoubleUnaryOperator operator, String visual) {
		super(a, visual+"(",")");
		this.operator=operator;
	}
	
	public UnaryOperatorMExpression(DoubleMExpression a, DoubleUnaryOperator operator) {
		this(a,operator, operator.getClass().getSimpleName());
	}
	
	@Override
	public double pget(Map<MExpressionParameter<?>, Object> parameters) {
		return operator.applyAsDouble(a.pget(parameters));
	}
	
}