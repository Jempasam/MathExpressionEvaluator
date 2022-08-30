package jempasam.mexpression.tree.doubletype.simple;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jempasam.mexpression.tree.MExpressionParameter;
import jempasam.mexpression.tree.doubletype.DoubleMExpression;

public class NumberMExpression implements DoubleMExpression {
	
	
	private double value;
	private String visual;
	
	
	public NumberMExpression(double value) {
		super();
		this.value = value;
		this.visual = Double.toString(value);
	}


	@Override
	public double pget(Map<MExpressionParameter<?>, Object> parameters) {
		return value;
	}
	
	@Override
	public Set<MExpressionParameter<?>> getParameters() {
		return new HashSet<>();
	}
	
	@Override
	public String getVisual() {
		return visual;
	}
	
}
