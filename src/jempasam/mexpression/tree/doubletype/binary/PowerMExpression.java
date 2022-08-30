package jempasam.mexpression.tree.doubletype.binary;

import java.util.Map;

import jempasam.mexpression.tree.MExpressionParameter;
import jempasam.mexpression.tree.doubletype.DoubleMExpression;

public class PowerMExpression extends BinaryDoubleMExpression {

	public PowerMExpression(DoubleMExpression a, DoubleMExpression b) {
		super(a, b, "^");
	}
	
	@Override
	public double pget(Map<MExpressionParameter<?>, Object> parameters) {
		return Math.pow(a.pget(parameters),b.pget(parameters));
	}
	
}
