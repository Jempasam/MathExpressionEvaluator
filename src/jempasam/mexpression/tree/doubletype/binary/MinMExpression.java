package jempasam.mexpression.tree.doubletype.binary;

import java.util.Map;

import jempasam.mexpression.tree.MExpressionParameter;
import jempasam.mexpression.tree.doubletype.DoubleMExpression;

public class MinMExpression extends BinaryDoubleMExpression {

	public MinMExpression(DoubleMExpression a, DoubleMExpression b) {
		super(a, b, "min");
	}
	
	@Override
	public double pget(Map<MExpressionParameter<?>, Object> parameters) {
		return Math.min(a.pget(parameters),b.pget(parameters));
	}
	
}
