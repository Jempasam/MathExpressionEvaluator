package jempasam.mexpression.tree.logical;

import java.util.Map;
import java.util.Set;

import jempasam.mexpression.tree.MExpression;
import jempasam.mexpression.tree.MExpressionParameter;
import jempasam.mexpression.tree.booltype.BooleanMExpression;

public class ConditionnalMExpression<T> implements MExpression<T> {
	
	private BooleanMExpression condition;
	private MExpression<? extends T> then;
	private MExpression<? extends T> els;
	

	public ConditionnalMExpression(BooleanMExpression condition, MExpression<? extends T> then, MExpression<? extends T> els) {
		super();
		this.condition = condition;
		this.then = then;
		this.els = els;
	}
	

	@Override
	public T get(Map<MExpressionParameter<?>, Object> parameters) {
		if(condition.get(parameters)) return then.get(parameters);
		else return els.get(parameters);
	}

	@Override
	public String getVisual() {
		StringBuilder sb=new StringBuilder();
		sb.append("(").append(condition.getVisual()).append(" ? ").append(then.getVisual()).append(" : ").append(els.getVisual()).append(")");
		return sb.toString();
	}

	@Override
	public Set<MExpressionParameter<?>> getParameters() {
		Set<MExpressionParameter<?>> ret=condition.getParameters();
		ret.addAll(then.getParameters());
		ret.addAll(els.getParameters());
		return ret;
	}

}
