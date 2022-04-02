package equation.tree.binary;

import java.util.List;

import equation.tree.Equation;

public abstract class BinaryEquation implements Equation{

	
	protected Equation a;
	protected Equation b;
	private String visual;
	
	
	public BinaryEquation(Equation a, Equation b, String sign) {
		super();
		this.a = a;
		this.b = b;
		this.visual="("+a.getVisual()+sign+b.getVisual()+")";
	}
	
	@Override
	public List<String> getParameters() {
		List<String> ret=a.getParameters();
		ret.addAll(b.getParameters());
		return ret;
	}
	
	@Override
	public String getVisual() {
		return visual;
	}
}
