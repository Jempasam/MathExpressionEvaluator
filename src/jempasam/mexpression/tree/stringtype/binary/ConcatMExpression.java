package jempasam.mexpression.tree.stringtype.binary;

import java.util.Map;
import java.util.Set;

import jempasam.mexpression.tree.MExpressionParameter;
import jempasam.mexpression.tree.doubletype.DoubleMExpression;
import jempasam.mexpression.tree.stringtype.StringMExpression;

public class ConcatMExpression implements StringMExpression{
	
	
	
	protected StringMExpression a;
	protected StringMExpression b;
	private String visual;
	
	
	
	public ConcatMExpression(StringMExpression a, StringMExpression b) {
		super();
		this.a = a;
		this.b = b;
		this.visual="("+a.getVisual()+"&&"+b.getVisual()+")";
	}
	
	
	
	@Override
	public Set<MExpressionParameter<?>> getParameters() {
		Set<MExpressionParameter<?>> ret=a.getParameters();
		ret.addAll(b.getParameters());
		return ret;
	}
	
	@Override
	public String getVisual() {
		return visual;
	}
	
	@Override
	public String get(Map<MExpressionParameter<?>, Object> parameters) {
		return a.get(parameters)+b.get(parameters);
	}
}
