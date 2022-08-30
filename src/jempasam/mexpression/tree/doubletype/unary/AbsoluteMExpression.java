package jempasam.mexpression.tree.doubletype.unary;

import java.util.Map;

import jempasam.mexpression.tree.MExpressionParameter;
import jempasam.mexpression.tree.doubletype.DoubleMExpression;

public class AbsoluteMExpression extends UnaryDoubleMExpression {

	public AbsoluteMExpression(DoubleMExpression a) {
		super(a, "|","|");
	}
	
	@Override
	public double pget(Map<MExpressionParameter<?>, Object> parameters) {
		return Math.abs(a.pget(parameters));
	}
	
}
