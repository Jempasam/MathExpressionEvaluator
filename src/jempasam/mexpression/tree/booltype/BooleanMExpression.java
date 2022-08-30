package jempasam.mexpression.tree.booltype;

import java.util.Map;

import jempasam.mexpression.tree.MExpression;
import jempasam.mexpression.tree.MExpressionParameter;

public interface BooleanMExpression extends MExpression<Boolean>{
	boolean pget(Map<MExpressionParameter<?>, Object> parameters);
	default Boolean get(Map<MExpressionParameter<?>, Object> parameters) {
		return pget(parameters);
	}
}
