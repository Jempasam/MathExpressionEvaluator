package jempasam.mexpression.tree.stringtype.simple;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jempasam.mexpression.tree.MExpressionParameter;
import jempasam.mexpression.tree.stringtype.StringMExpression;

public class StringLitteralMExpression implements StringMExpression {
	
	
	private String value;
	private String visual;
	
	
	public StringLitteralMExpression(String value) {
		super();
		this.value = value;
		this.visual = "\""+value+"\"";
	}


	@Override
	public String get(Map<MExpressionParameter<?>, Object> parameters) {
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
