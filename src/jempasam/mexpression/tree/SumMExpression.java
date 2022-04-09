package jempasam.mexpression.tree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SumMExpression implements MExpression {
	
	private String variable;
	private MExpression from;
	private MExpression to;
	private MExpression target;
	private String visual;

	public SumMExpression(String variable, MExpression from, MExpression to, MExpression target) {
		this.visual="∑"+variable+"("+from.getVisual()+")→("+to.getVisual()+"):("+target.getVisual()+")";
		this.variable=variable;
		this.from=from;
		this.to=to;
		this.target=target;
	}
	
	@Override
	public double get(Map<String, Double> parameters) {
		double sum=0;
		Map<String,Double> para=new HashMap<>(parameters);
		double f=from.get(parameters);
		double t=to.get(parameters);
		for(double i=f; i<=t; i++) {
			para.put(variable, i);
			sum+=target.get(para);
		}
		return sum;
	}
	
	@Override
	public Set<String> getParameters() {
		Set<String> ret=target.getParameters();
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
