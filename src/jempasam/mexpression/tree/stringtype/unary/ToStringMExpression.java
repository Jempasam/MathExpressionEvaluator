package jempasam.mexpression.tree.stringtype.unary;

import java.util.Map;
import java.util.Set;

import jempasam.mexpression.tree.MExpression;
import jempasam.mexpression.tree.MExpressionParameter;
import jempasam.mexpression.tree.stringtype.StringMExpression;

public class ToStringMExpression implements StringMExpression{
	
	
	
	protected MExpression<?> a;
	private String visual;
	
	
	
	public ToStringMExpression(MExpression<? extends Number> a) {
		super();
		this.a = a;
		this.visual="str("+a.getVisual()+")";
	}
	
	
	
	@Override
	public Set<MExpressionParameter<?>> getParameters() {
		return a.getParameters();
	}
	
	@Override
	public String getVisual() {
		return visual;
	}
	
	@Override
	public String get(Map<MExpressionParameter<?>, Object> parameters) {
		return a.get(parameters).toString();
	}
}
