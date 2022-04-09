package jempasam.mexpression.tree;

import java.util.regex.Pattern;

import jempasam.mexpression.tree.builder.MExpressionTerm;
import jempasam.mexpression.tree.serializer.PatternListMExpressionSerializer;

public class MExpressions {
	
	private MExpressions() { }
	
	public static PatternListMExpressionSerializer createBaseMExpressionSerializer() {
		PatternListMExpressionSerializer ret=new PatternListMExpressionSerializer();
		ret.register(Pattern.compile("^\\+$"), MExpressionTerm.ADD);
		ret.register(Pattern.compile("^\\-$"), MExpressionTerm.MINUS);
		ret.register(Pattern.compile("^\\*$"), MExpressionTerm.MULTIPLY);
		ret.register(Pattern.compile("^\\/$"), MExpressionTerm.DIVIDE);
		ret.register(Pattern.compile("^\\^$"), MExpressionTerm.POWER);
		
		ret.register(Pattern.compile("^e$"), MExpressionTerm.E);
		ret.register(Pattern.compile("^pi$"), MExpressionTerm.PI);
		ret.register(Pattern.compile("^true$"), MExpressionTerm.TRUE);
		ret.register(Pattern.compile("^false$"), MExpressionTerm.FALSE);
		
		ret.register(Pattern.compile("^abs$"), MExpressionTerm.ABSOLUTE);
		ret.register(Pattern.compile("^sqrt$"), MExpressionTerm.SQRT);
		
		ret.register(Pattern.compile("^cos$"), MExpressionTerm.COS);
		ret.register(Pattern.compile("^arccos$"), MExpressionTerm.ARCCOS);
		ret.register(Pattern.compile("^sin$"), MExpressionTerm.SIN);
		ret.register(Pattern.compile("^arcsin$"), MExpressionTerm.ARCSIN);
		ret.register(Pattern.compile("^tan$"), MExpressionTerm.TAN);
		ret.register(Pattern.compile("^arctan$"), MExpressionTerm.ARCTAN);
		
		ret.register(Pattern.compile("^max$"), MExpressionTerm.MAX);
		ret.register(Pattern.compile("^min$"), MExpressionTerm.MIN);
		
		ret.register(Pattern.compile("^mid$"), MExpressionTerm.MIDDLE);
		
		ret.register(Pattern.compile("^!$"), MExpressionTerm.FACTORIAL);
		
		ret.register(Pattern.compile("^exp$"), MExpressionTerm.EXP);
		ret.register(Pattern.compile("^log$"), MExpressionTerm.LOG);
		ret.register(Pattern.compile("^log10$"), MExpressionTerm.LOG10);
		
		ret.register(Pattern.compile("^floor$"), MExpressionTerm.FLOOR);
		ret.register(Pattern.compile("^ceil$"), MExpressionTerm.CEIL);
		
		ret.register(Pattern.compile("^\\($"), MExpressionTerm.OPEN);
		ret.register(Pattern.compile("^\\)$"), MExpressionTerm.CLOSE);
		
		ret.register(Pattern.compile("^\\>$"), MExpressionTerm.GREATER_THAN);
		ret.register(Pattern.compile("^\\<$"), MExpressionTerm.LOWER_THAN);
		ret.register(Pattern.compile("^\\=$"), MExpressionTerm.EQUALS);
		
		ret.register(Pattern.compile("^and$"), MExpressionTerm.AND);
		ret.register(Pattern.compile("^or$"), MExpressionTerm.OR);
		
		ret.register(Pattern.compile("^not$"), MExpressionTerm.NOT);
		
		ret.register(Pattern.compile("^\\-?[0-9]+.?[0-9]*$"), (str)->MExpressionTerm.of(Double.parseDouble(str)));
		ret.register(Pattern.compile("^[a-zA-Z]$"), (str)->MExpressionTerm.of(str));
		
		ret.register(Pattern.compile("^Sum:[a-zA-Z]$"), (str)->MExpressionTerm.sum(str.substring(4, 5)));
		ret.register(Pattern.compile("^[a-zA-Z]'$"), (str)->MExpressionTerm.derivative(str.substring(0, str.length()-1)));
		
		return ret;
	}
}
