package jempasam.mexpression.tree.unary;

import java.util.Map;

import jempasam.mexpression.tree.MExpression;

public class FactorialMExpression extends UnaryMExpression {

	public FactorialMExpression(MExpression a) {
		super(a, "(",")!");
	}
	
	@Override
	public double get(Map<String, Double> parameters) {
		double result=1;
		int max=(int)a.get(parameters);
		for(int i=2; i<=max; i++)result*=i;
		return result;
	}
	
}
