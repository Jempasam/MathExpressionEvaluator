package jempasam.mexpression.tree.booltype.binary;

import java.util.Map;

import jempasam.mexpression.tree.MExpressionParameter;
import jempasam.mexpression.tree.booltype.BooleanMExpression;

public class AndMExpression extends BinaryBoolMExpression {

	public AndMExpression(BooleanMExpression a, BooleanMExpression b) {
		super(a, b, " and ");
	}
	
	@Override
	public boolean pget(Map<MExpressionParameter<?>, Object> parameters) {
		return a.pget(parameters)&&b.pget(parameters);
	}
	
}
