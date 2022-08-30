package jempasam.mexpression.tree.doubletype.simple;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jempasam.mexpression.tree.MExpressionParameter;
import jempasam.mexpression.tree.doubletype.DoubleMExpression;

public class DoubleParameterMExpression implements DoubleMExpression {

	
	private MExpressionParameter<Double> name;	
	
	
	public DoubleParameterMExpression(String name) {
		super();
		this.name=new MExpressionParameter<>(Double.class, name);
	}

	@Override
	public double pget(Map<MExpressionParameter<?>, Object> parameters) {
		return (Double)parameters.get(name);
	}
	
	@Override
	public Set<MExpressionParameter<?>> getParameters() {
		Set<MExpressionParameter<?>> ret=new HashSet<>();
		ret.add(name);
		return ret;
	}

	@Override
	public String getVisual() {
		return name.getName();
	}

}
