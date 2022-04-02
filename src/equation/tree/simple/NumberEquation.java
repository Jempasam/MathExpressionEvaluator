package equation.tree.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import equation.tree.Equation;

public class NumberEquation implements Equation {
	
	
	private double value;
	private String visual;
	
	
	public NumberEquation(double value) {
		super();
		this.value = value;
		this.visual = Double.toString(value);
	}


	@Override
	public double get(Map<String, Double> parameters) {
		return value;
	}
	
	@Override
	public List<String> getParameters() {
		return new ArrayList<>();
	}
	
	@Override
	public String getVisual() {
		return visual;
	}
	
}
