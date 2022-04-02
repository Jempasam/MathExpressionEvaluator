package mexpression.tree.binary;

import java.util.Map;

import mexpression.tree.MExpression;

public class DivideMExpression extends BinaryMExpression {

	public DivideMExpression(MExpression a, MExpression b) {
		super(a, b, "÷");
	}
	
	@Override
	public double get(Map<String, Double> parameters) {
		return a.get(parameters)/b.get(parameters);
	}
	
}
