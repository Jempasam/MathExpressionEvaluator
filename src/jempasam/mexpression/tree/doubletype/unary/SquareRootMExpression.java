package jempasam.mexpression.tree.doubletype.unary;

import java.util.Map;

import jempasam.mexpression.tree.MExpressionParameter;
import jempasam.mexpression.tree.doubletype.DoubleMExpression;

public class SquareRootMExpression extends UnaryDoubleMExpression {

	public SquareRootMExpression(DoubleMExpression a) {
		super(a, "sqrt(",")");
	}
	
	@Override
	public double pget(Map<MExpressionParameter<?>, Object> parameters) {
		return Math.sqrt(a.pget(parameters));
	}
	
}
