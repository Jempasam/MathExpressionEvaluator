package jempasam.mexpression.tree.doubletype.other;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jempasam.mexpression.tree.MExpressionParameter;
import jempasam.mexpression.tree.doubletype.DoubleMExpression;

public class SumMExpression implements DoubleMExpression {
	
	private MExpressionParameter<?> variable;
	private DoubleMExpression from;
	private DoubleMExpression to;
	private DoubleMExpression target;
	private String visual;

	public SumMExpression(String variable, DoubleMExpression from, DoubleMExpression to, DoubleMExpression target) {
		this.visual="∑"+variable+"("+from.getVisual()+")→("+to.getVisual()+"):("+target.getVisual()+")";
		this.variable=new MExpressionParameter<>(Double.TYPE, variable);
		this.from=from;
		this.to=to;
		this.target=target;
	}
	
	@Override
	public double pget(Map<MExpressionParameter<?>, Object> parameters) {
		double sum=0;
		Map<MExpressionParameter<?>, Object> para=new HashMap<>(parameters);
		double f=from.pget(parameters);
		double t=to.pget(parameters);
		for(double i=f; i<=t; i++) {
			para.put(variable, i);
			sum+=target.pget(para);
		}
		return sum;
	}
	
	@Override
	public Set<MExpressionParameter<?>> getParameters() {
		Set<MExpressionParameter<?>> ret=target.getParameters();
		ret.remove(variable);
		ret.addAll(from.getParameters());
		ret.addAll(to.getParameters());
		return ret;
	}
	
	@Override
	public String getVisual() {
		return visual;
	}
}
