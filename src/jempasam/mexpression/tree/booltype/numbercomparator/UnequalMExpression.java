package jempasam.mexpression.tree.booltype.numbercomparator;

import java.util.Map;

import jempasam.mexpression.tree.MExpressionParameter;
import jempasam.mexpression.tree.doubletype.DoubleMExpression;

public class UnequalMExpression extends NumberComparatorMExpression {

	public UnequalMExpression(DoubleMExpression a, DoubleMExpression b) {
		super(a, b, "<>");
	}
	
	@Override
	public boolean pget(Map<MExpressionParameter<?>, Object> parameters) {
		return !a.get(parameters).equals(b.get(parameters));
	}
	
}
