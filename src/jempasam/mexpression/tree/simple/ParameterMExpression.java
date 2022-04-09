package jempasam.mexpression.tree.simple;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jempasam.mexpression.tree.MExpression;

public class ParameterMExpression implements MExpression {

	
	private String name;	
	
	
	public ParameterMExpression(String name) {
		super();
		this.name=name;
	}

	@Override
	public double get(Map<String, Double> parameters) {
		return parameters.get(name);
	}
	
	@Override
	public Set<String> getParameters() {
		Set<String> ret=new HashSet<>();
		ret.add(name);
		return ret;
	}

	@Override
	public String getVisual() {
		return name;
	}

}
