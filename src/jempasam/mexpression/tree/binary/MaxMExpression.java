package jempasam.mexpression.tree.binary;

import java.util.Map;

import jempasam.mexpression.tree.MExpression;

public class MaxMExpression extends BinaryMExpression {

	public MaxMExpression(MExpression a, MExpression b) {
		super(a, b, "max");
	}
	
	@Override
	public double get(Map<String, Double> parameters) {
		return Math.max(a.get(parameters),b.get(parameters));
	}
	
}
