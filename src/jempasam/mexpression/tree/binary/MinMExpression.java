package jempasam.mexpression.tree.binary;

import java.util.Map;

import jempasam.mexpression.tree.MExpression;

public class MinMExpression extends BinaryMExpression {

	public MinMExpression(MExpression a, MExpression b) {
		super(a, b, "min");
	}
	
	@Override
	public double get(Map<String, Double> parameters) {
		return Math.min(a.get(parameters),b.get(parameters));
	}
	
}
