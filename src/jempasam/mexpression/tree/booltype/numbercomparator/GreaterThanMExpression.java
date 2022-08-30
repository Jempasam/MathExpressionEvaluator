package jempasam.mexpression.tree.booltype.numbercomparator;

import java.util.Map;

import jempasam.mexpression.tree.MExpressionParameter;
import jempasam.mexpression.tree.doubletype.DoubleMExpression;

public class GreaterThanMExpression extends NumberComparatorMExpression {

	public GreaterThanMExpression(DoubleMExpression a, DoubleMExpression b) {
		super(a, b, ">");
	}
	
	@Override
	public boolean pget(Map<MExpressionParameter<?>, Object> parameters) {
		return a.get(parameters).doubleValue()>b.get(parameters).doubleValue();
	}
	
}
