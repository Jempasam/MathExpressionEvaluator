package jempasam.mexpression.tree.builder.term;

import java.util.List;

import jempasam.mexpression.tree.MExpression;
import jempasam.mexpression.tree.doubletype.DoubleMExpression;

public interface MExpressionTerm {
	
	public default MExpressionTermArgument[] getArgumentsPlaces() { return new MExpressionTermArgument[0]; }
	
	public MExpression<?> from(List<MExpression<?>> args);
	
	public int getPriority();
	
	public int getDirection();
	
}
