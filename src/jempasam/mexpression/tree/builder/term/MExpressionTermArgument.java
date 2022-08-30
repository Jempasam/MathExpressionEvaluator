package jempasam.mexpression.tree.builder.term;

public class MExpressionTermArgument {
	
	
	
	private int pos;
	private Class<?> type;
	private boolean required;
	
	
	
	public MExpressionTermArgument(int pos, Class<?> type, boolean required) {
		super();
		this.pos = pos;
		this.type = type;
		this.required = required;
	}



	public int getPos() {
		return pos;
	}

	public Class<?> getType() {
		return type;
	}

	public boolean isRequired() {
		return required;
	}
	
}
