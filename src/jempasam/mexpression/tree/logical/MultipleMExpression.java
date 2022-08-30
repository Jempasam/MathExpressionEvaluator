package jempasam.mexpression.tree.logical;

import java.util.Map;
import java.util.Set;

import jempasam.mexpression.tree.MExpression;
import jempasam.mexpression.tree.MExpressionParameter;

public class MultipleMExpression<T> implements MExpression<T> {
	
	
	
	private MExpression<T> expressions[];
	
	
	
	public MultipleMExpression(MExpression<T>[] expressions) {
		super();
		this.expressions = expressions;
	}
	
	

	@Override
	public T get(Map<MExpressionParameter<?>, Object> parameters) {
		int last=expressions.length-1;
		for(int i=0; i<last; i++)expressions[i].get(parameters);
		return expressions[last].get(parameters);
	}

	@Override
	public String getVisual() {
		StringBuilder sb=new StringBuilder();
		sb.append("{");
		int last=expressions.length-1;
		for(int i=0; i<last; i++)sb.append(expressions[i].getVisual()).append(";");
		sb.append("return ").append(expressions[last]).append(";}");
		return sb.toString();
	}

	@Override
	public Set<MExpressionParameter<?>> getParameters() {
		Set<MExpressionParameter<?>> ret=expressions[0].getParameters();
		for(int i=1; i<expressions.length; i++)ret.addAll(expressions[i].getParameters());
		return ret;
	}

}
