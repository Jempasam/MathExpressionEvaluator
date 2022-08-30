package jempasam.mexpression.tree.doubletype.binary;

import java.util.Map;

import jempasam.mexpression.tree.MExpressionParameter;
import jempasam.mexpression.tree.doubletype.DoubleMExpression;

public class MaxMExpression extends BinaryDoubleMExpression {

	public MaxMExpression(DoubleMExpression a, DoubleMExpression b) {
		super(a, b, "max");
	}
	
	@Override
	public double pget(Map<MExpressionParameter<?>, Object> parameters) {
		return Math.max(a.pget(parameters),b.pget(parameters));
	}
	
}
