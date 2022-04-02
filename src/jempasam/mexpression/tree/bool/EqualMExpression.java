package jempasam.mexpression.tree.bool;

import java.util.Map;

import jempasam.mexpression.tree.MExpression;
import jempasam.mexpression.tree.binary.BinaryMExpression;

public class EqualMExpression extends BinaryMExpression {

	public EqualMExpression(MExpression a, MExpression b) {
		super(a, b, "=");
	}
	
	@Override
	public double get(Map<String, Double> parameters) {
		return a.get(parameters)==b.get(parameters) ? 1d : 0d;
	}
	
}