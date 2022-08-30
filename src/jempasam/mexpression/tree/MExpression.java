package jempasam.mexpression.tree;

import java.util.Map;
import java.util.Set;

public interface MExpression<T> {
	String getVisual();
	Set<MExpressionParameter<?>> getParameters();
	T get(Map<MExpressionParameter<?>, Object> parameters);
}
