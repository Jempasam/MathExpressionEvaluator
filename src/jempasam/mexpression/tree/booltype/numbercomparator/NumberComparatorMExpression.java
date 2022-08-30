package jempasam.mexpression.tree.booltype.numbercomparator;

import java.util.Set;

import jempasam.mexpression.tree.MExpression;
import jempasam.mexpression.tree.MExpressionParameter;
import jempasam.mexpression.tree.booltype.BooleanMExpression;

public abstract class NumberComparatorMExpression implements BooleanMExpression{

	
	protected MExpression<? extends Number> a;
	protected MExpression<? extends Number> b;
	private String visual;
	
	
	protected NumberComparatorMExpression(MExpression<? extends Number> a, MExpression<? extends Number> b, String sign) {
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
