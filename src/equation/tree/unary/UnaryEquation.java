package equation.tree.unary;

import java.util.List;

import equation.tree.Equation;

public abstract class UnaryEquation implements Equation{

	
	protected Equation a;
	private String visual;
	
	
	public UnaryEquation(Equation a, String before, String after) {
		super();
		this.a = a;
		this.visual=before+a.getVisual()+after;
	}
	
	@Override
	public List<String> getParameters() {
		return a.getParameters();
	}
	
	@Override
	public String getVisual() {
		return visual;
	}
}
