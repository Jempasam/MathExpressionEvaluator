package jempasam.mexpression.tree.booltype.unary;

import java.util.Set;

import jempasam.mexpression.tree.MExpressionParameter;
import jempasam.mexpression.tree.booltype.BooleanMExpression;

public abstract class UnaryBoolMExpression implements BooleanMExpression{

	
	protected BooleanMExpression a;
	private String visual;
	
	
	protected UnaryBoolMExpression(BooleanMExpression a, String prefix, String suffix) {
		super();
		this.a = a;
		this.visual=prefix+a.getVisual()+suffix;
	}
	
	@Override
	public Set<MExpressionParameter<?>> getParameters() {
		return a.getParameters();
	}
	
	@Override
	public String getVisual() {
		return visual;
	}
}
