package jempasam.mexpression.tree.doubletype.unary;

import java.util.Map;

import jempasam.mexpression.tree.MExpressionParameter;
import jempasam.mexpression.tree.doubletype.DoubleMExpression;

public class FactorialMExpression extends UnaryDoubleMExpression {

	public FactorialMExpression(DoubleMExpression a) {
		super(a, "(",")!");
	}
	
	@Override
	public double pget(Map<MExpressionParameter<?>, Object> parameters) {
		double result=1;
		int max=(int)a.pget(parameters);
		for(int i=2; i<=max; i++)result*=i;
		return result;
	}
	
}
