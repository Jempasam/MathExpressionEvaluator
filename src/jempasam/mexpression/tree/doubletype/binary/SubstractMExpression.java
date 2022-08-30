package jempasam.mexpression.tree.doubletype.binary;

import java.util.Map;

import jempasam.mexpression.tree.MExpressionParameter;
import jempasam.mexpression.tree.doubletype.DoubleMExpression;

public class SubstractMExpression extends BinaryDoubleMExpression {

	public SubstractMExpression(DoubleMExpression a, DoubleMExpression b) {
		super(a, b, "-");
	}
	
	@Override
	public double pget(Map<MExpressionParameter<?>, Object> parameters) {
		return a.pget(parameters)-b.pget(parameters);
	}
	
}
