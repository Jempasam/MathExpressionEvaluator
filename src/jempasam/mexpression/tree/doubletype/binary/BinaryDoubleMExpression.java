package jempasam.mexpression.tree.doubletype.binary;

import java.util.Set;

import jempasam.mexpression.tree.MExpressionParameter;
import jempasam.mexpression.tree.doubletype.DoubleMExpression;

public abstract class BinaryDoubleMExpression implements DoubleMExpression{

	
	protected DoubleMExpression a;
	protected DoubleMExpression b;
	private String visual;
	
	
	public BinaryDoubleMExpression(DoubleMExpression a, DoubleMExpression b, String sign) {
		super();
		this.a = a;
		this.b = b;
		this.visual="("+a.getVisual()+sign+b.getVisual()+")";
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
}
