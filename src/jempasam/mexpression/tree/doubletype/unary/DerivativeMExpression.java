package jempasam.mexpression.tree.doubletype.unary;

import java.util.Map;

import jempasam.mexpression.tree.MExpressionParameter;
import jempasam.mexpression.tree.doubletype.DoubleMExpression;

public class DerivativeMExpression extends UnaryDoubleMExpression {
	
	private MExpressionParameter<Double> variable;
	
	public DerivativeMExpression(DoubleMExpression a, String variable) {
		super(a, "∂"+variable+"(",")");
		this.variable=new MExpressionParameter<>(Double.TYPE, variable);
	}
	
	@Override
	public double pget(Map<MExpressionParameter<?>, Object> parameters) {
		double from=(Double)parameters.get(variable);
		double to=from+0.000000001d;
		double offset=to-from;
		
		double aa=a.pget(parameters);
		parameters.put(variable, to);
		double bb=a.pget(parameters);
		parameters.put(variable, from);
		
		return (bb-aa)/offset;
	}
	
}
