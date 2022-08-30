package jempasam.mexpression.tree.doubletype;

import java.util.Map;

import jempasam.mexpression.tree.MExpression;
import jempasam.mexpression.tree.MExpressionParameter;

public interface DoubleMExpression extends MExpression<Double>{
	
	double pget(Map<MExpressionParameter<?>, Object> parameters);
	
	@Override
	default Double get(Map<MExpressionParameter<?>, Object> parameters) {
		return pget(parameters);
	}
	
	
	
}
