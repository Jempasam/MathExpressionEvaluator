package jempasam.mexpression.tree;

public class MExpressionParameter<T> {
	
	
	
	private String name;
	private Class<T> type;
	
	
	
	public MExpressionParameter(Class<T> type, String name) {
		super();
		this.type = type;
		this.name = name;
	}



	public String getName() {
		return name;
	}

	public Class<T> getType() {
		return type;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode()+type.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof MExpressionParameter && ((MExpressionParameter<?>)obj).type.equals(type) && ((MExpressionParameter<?>)obj).name.equals(name);
	}
	
}
