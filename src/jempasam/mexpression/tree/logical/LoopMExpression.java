package jempasam.mexpression.tree.logical;

import java.util.Map;
import java.util.Set;

import jempasam.mexpression.tree.MExpression;
import jempasam.mexpression.tree.MExpressionParameter;
import jempasam.mexpression.tree.booltype.BooleanMExpression;
import jempasam.mexpression.tree.doubletype.DoubleMExpression;

public class LoopMExpression<T> implements MExpression<T> {
	
	
	
	private BooleanMExpression condition;
	private MExpression<? extends T> before;
	private MExpression<? extends T> after;
	
	
	
	public LoopMExpression(BooleanMExpression condition, MExpression<? extends T> before, MExpression<? extends T> after) {
		super();
		this.condition = condition;
		this.before = before;
		this.after = after;
	}
	
	

	@Override
	public T get(Map<MExpressionParameter<?>, Object> parameters) {
		T ret=null;
		while(true) {
			ret=before.get(parameters);
			if(condition.get(parameters))break;
			ret=after.get(parameters);
		}
		return ret;
	}

	@Override
	public String getVisual() {
		StringBuilder sb=new StringBuilder();
		sb.append("loop{").append(before.getVisual()).append("; if(").append(condition.getVisual()).append(")break; ").append(after.getVisual()).append(";}");
		return sb.toString();
	}

	@Override
	public Set<MExpressionParameter<?>> getParameters() {
		Set<MExpressionParameter<?>> ret=condition.getParameters();
		ret.addAll(after.getParameters());
		ret.addAll(before.getParameters());
		return ret;
	}

}
