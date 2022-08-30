package jempasam.mexpression.tree.booltype.binary;

import java.util.Map;

import jempasam.mexpression.tree.MExpressionParameter;
import jempasam.mexpression.tree.booltype.BooleanMExpression;

public class XorMExpression extends BinaryBoolMExpression {

	public XorMExpression(BooleanMExpression a, BooleanMExpression b) {
		super(a, b, " xor ");
	}
	
	@Override
	public boolean pget(Map<MExpressionParameter<?>, Object> parameters) {
		return a.pget(parameters)^b.pget(parameters);
	}
	
}
