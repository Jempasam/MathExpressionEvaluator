package equation.tree.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import equation.tree.Equation;

public class ParameterEquation implements Equation {

	
	private String name;	
	
	
	public ParameterEquation(String name) {
		super();
		this.name=name;
	}

	@Override
	public double get(Map<String, Double> parameters) {
		return parameters.get(name);
	}
	
	@Override
	public List<String> getParameters() {
		List<String> ret=new ArrayList<>();
		ret.add(name);
		return ret;
	}

	@Override
	public String getVisual() {
		return name;
	}

}
