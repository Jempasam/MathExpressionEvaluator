package jempasam.mexpression.tree.binary;

import java.util.Map;

import jempasam.mexpression.tree.MExpression;

public class ModuloMExpression extends BinaryMExpression {

	public ModuloMExpression(MExpression a, MExpression b) {
		super(a, b, "%");
	}
	
	@Override
	public double get(Map<String, Double> parameters) {
		return a.get(parameters)%b.get(parameters);
	}
	
}
