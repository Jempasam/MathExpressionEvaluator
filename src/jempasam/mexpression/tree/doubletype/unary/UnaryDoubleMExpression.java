package jempasam.mexpression.tree.doubletype.unary;

import java.util.Set;

import jempasam.mexpression.tree.MExpressionParameter;
import jempasam.mexpression.tree.doubletype.DoubleMExpression;

public abstract class UnaryDoubleMExpression implements DoubleMExpression{

	
	protected DoubleMExpression a;
	private String visual;
	
	
	public UnaryDoubleMExpression(DoubleMExpression a, String before, String after) {
		super();
		this.a = a;
		this.visual=before+a.getVisual()+after;
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
	public String toString() {
		return visual;
	}
}
