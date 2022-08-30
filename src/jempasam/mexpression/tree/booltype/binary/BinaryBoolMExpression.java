package jempasam.mexpression.tree.booltype.binary;

import java.util.Set;

import jempasam.mexpression.tree.MExpressionParameter;
import jempasam.mexpression.tree.booltype.BooleanMExpression;

public abstract class BinaryBoolMExpression implements BooleanMExpression{

	
	protected BooleanMExpression a;
	protected BooleanMExpression b;
	private String visual;
	
	
	protected BinaryBoolMExpression(BooleanMExpression a, BooleanMExpression b, String sign) {
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
