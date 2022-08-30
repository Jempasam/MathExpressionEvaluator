package jempasam.mexpression.tree.doubletype.binary;

import java.util.Map;

import jempasam.mexpression.tree.MExpressionParameter;
import jempasam.mexpression.tree.doubletype.DoubleMExpression;

public class MiddleMExpression extends BinaryDoubleMExpression {

	public MiddleMExpression(DoubleMExpression a, DoubleMExpression b) {
		super(a, b, "middle");
	}
	
	@Override
	public double pget(Map<MExpressionParameter<?>, Object> parameters) {
		return (a.pget(parameters)+b.pget(parameters))/2;
	}
	
}
