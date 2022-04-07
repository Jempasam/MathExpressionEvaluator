package jempasam.mexpression.tree.binary;

import java.util.Map;

import jempasam.mexpression.tree.MExpression;

public class MiddleMExpression extends BinaryMExpression {

	public MiddleMExpression(MExpression a, MExpression b) {
		super(a, b, "middle");
	}
	
	@Override
	public double get(Map<String, Double> parameters) {
		return (a.get(parameters)+b.get(parameters))/2;
	}
	
}
