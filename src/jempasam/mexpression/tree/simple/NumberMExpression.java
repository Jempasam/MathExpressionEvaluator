package jempasam.mexpression.tree.simple;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jempasam.mexpression.tree.MExpression;

public class NumberMExpression implements MExpression {
	
	
	private double value;
	private String visual;
	
	
	public NumberMExpression(double value) {
		super();
		this.value = value;
		this.visual = Double.toString(value);
	}


	@Override
	public double get(Map<String, Double> parameters) {
		return value;
	}
	
	@Override
	public Set<String> getParameters() {
		return new HashSet<>();
	}
	
	@Override
	public String getVisual() {
		return visual;
	}
	
}
