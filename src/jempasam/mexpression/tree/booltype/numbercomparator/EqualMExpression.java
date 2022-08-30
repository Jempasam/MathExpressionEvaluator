package jempasam.mexpression.tree.booltype.numbercomparator;

import java.util.Map;

import jempasam.mexpression.tree.MExpressionParameter;
import jempasam.mexpression.tree.doubletype.DoubleMExpression;
import jempasam.mexpression.tree.doubletype.binary.BinaryDoubleMExpression;

public class EqualMExpression extends NumberComparatorMExpression {

	public EqualMExpression(DoubleMExpression a, DoubleMExpression b) {
		super(a, b, "=");
	}
	
	@Override
	public boolean pget(Map<MExpressionParameter<?>, Object> parameters) {
		return a.get(parameters).equals(b.get(parameters));
	}
	
}
