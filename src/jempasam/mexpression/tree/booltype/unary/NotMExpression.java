package jempasam.mexpression.tree.booltype.unary;

import java.util.Map;

import jempasam.mexpression.tree.MExpressionParameter;
import jempasam.mexpression.tree.booltype.BooleanMExpression;

public class NotMExpression extends UnaryBoolMExpression {

	public NotMExpression(BooleanMExpression a) {
		super(a, "not(",")");
	}
	
	@Override
	public boolean pget(Map<MExpressionParameter<?>, Object> parameters) {
		return !a.pget(parameters);
	}
	
}
