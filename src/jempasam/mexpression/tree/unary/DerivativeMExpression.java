package jempasam.mexpression.tree.unary;

import java.util.Map;

import jempasam.mexpression.tree.MExpression;

public class DerivativeMExpression extends UnaryMExpression {
	
	private String variable;
	
	public DerivativeMExpression(MExpression a, String variable) {
		super(a, "∂"+variable+"(",")");
		this.variable=variable;
	}
	
	@Override
	public double get(Map<String, Double> parameters) {
		double from=parameters.get(variable);
		double to=from+0.000000001d;
		double offset=to-from;
		
		double aa=a.get(parameters);
		parameters.put(variable, to);
		double bb=a.get(parameters);
		
		return (bb-aa)/offset;
	}
	
}
