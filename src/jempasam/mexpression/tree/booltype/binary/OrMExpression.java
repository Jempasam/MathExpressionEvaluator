package jempasam.mexpression.tree.booltype.binary;

import java.util.Map;

import jempasam.mexpression.tree.MExpressionParameter;
import jempasam.mexpression.tree.booltype.BooleanMExpression;

public class OrMExpression extends BinaryBoolMExpression {

	public OrMExpression(BooleanMExpression a, BooleanMExpression b) {
		super(a, b, " or ");
	}
	
	@Override
	public boolean pget(Map<MExpressionParameter<?>, Object> parameters) {
		return a.pget(parameters)||b.pget(parameters);
	}
	
}
