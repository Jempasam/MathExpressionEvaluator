package jempasam.mexpression.tree;

import java.util.Arrays;
import java.util.regex.Pattern;

import jempasam.mexpression.tree.builder.MExpressionBuilder;
import jempasam.mexpression.tree.builder.MExpressionBuilder.MExpressionBuilderException;
import jempasam.mexpression.tree.builder.term.MExpressionTerm;
import jempasam.mexpression.tree.builder.term.MExpressionTerms;
import jempasam.mexpression.tree.serializer.MExpressionSerializer;
import jempasam.mexpression.tree.serializer.MExpressionSerializer.MExpressionSerializerException;
import jempasam.mexpression.tree.serializer.PatternListMExpressionSerializer;

public class MExpressions {
	
	private MExpressions() { }
	
	public static PatternListMExpressionSerializer createBaseMExpressionSerializer() {
		PatternListMExpressionSerializer ret=new PatternListMExpressionSerializer();
		ret.register(Pattern.compile("^\\+$"), MExpressionTerms.ADD);
		ret.register(Pattern.compile("^\\-$"), MExpressionTerms.MINUS);
		ret.register(Pattern.compile("^\\*$"), MExpressionTerms.MULTIPLY);
		ret.register(Pattern.compile("^\\/$"), MExpressionTerms.DIVIDE);
		ret.register(Pattern.compile("^\\^$"), MExpressionTerms.POWER);
		ret.register(Pattern.compile("^\\%$"), MExpressionTerms.MODULO);
		
		ret.register(Pattern.compile("^e$"), MExpressionTerms.E);
		ret.register(Pattern.compile("^pi$"), MExpressionTerms.PI);
		ret.register(Pattern.compile("^true$"), MExpressionTerms.TRUE);
		ret.register(Pattern.compile("^false$"), MExpressionTerms.FALSE);
		
		ret.register(Pattern.compile("^abs$"), MExpressionTerms.ABSOLUTE);
		ret.register(Pattern.compile("^sqrt$"), MExpressionTerms.SQRT);
		
		ret.register(Pattern.compile("^cos$"), MExpressionTerms.COS);
		ret.register(Pattern.compile("^arccos$"), MExpressionTerms.ARCCOS);
		ret.register(Pattern.compile("^sin$"), MExpressionTerms.SIN);
		ret.register(Pattern.compile("^arcsin$"), MExpressionTerms.ARCSIN);
		ret.register(Pattern.compile("^tan$"), MExpressionTerms.TAN);
		ret.register(Pattern.compile("^arctan$"), MExpressionTerms.ARCTAN);
		
		ret.register(Pattern.compile("^max$"), MExpressionTerms.MAX);
		ret.register(Pattern.compile("^min$"), MExpressionTerms.MIN);
		
		ret.register(Pattern.compile("^mid$"), MExpressionTerms.MIDDLE);
		
		ret.register(Pattern.compile("^!$"), MExpressionTerms.FACTORIAL);
		
		ret.register(Pattern.compile("^str$"), MExpressionTerms.TOSTRING);
		ret.register(Pattern.compile("^exp$"), MExpressionTerms.EXP);
		ret.register(Pattern.compile("^log$"), MExpressionTerms.LOG);
		ret.register(Pattern.compile("^log10$"), MExpressionTerms.LOG10);
		
		ret.register(Pattern.compile("^floor$"), MExpressionTerms.FLOOR);
		ret.register(Pattern.compile("^ceil$"), MExpressionTerms.CEIL);
		
		ret.register(Pattern.compile("^\\($"), MExpressionTerms.OPEN);
		ret.register(Pattern.compile("^\\)$"), MExpressionTerms.CLOSE);
		
		ret.register(Pattern.compile("^\\>$"), MExpressionTerms.GREATER_THAN);
		ret.register(Pattern.compile("^\\<$"), MExpressionTerms.LOWER_THAN);
		ret.register(Pattern.compile("^\\=$"), MExpressionTerms.EQUAL);
		
		ret.register(Pattern.compile("^and$"), MExpressionTerms.AND);
		ret.register(Pattern.compile("^or$"), MExpressionTerms.OR);
		
		ret.register(Pattern.compile("^not$"), MExpressionTerms.NOT);
		
		ret.register(Pattern.compile("^\\{$"), Arrays.asList(MExpressionTerms.MULTIPLE,MExpressionTerms.OPEN));
		ret.register(Pattern.compile("^\\}$"), MExpressionTerms.CLOSE);
		ret.register(Pattern.compile("^\\?$"), MExpressionTerms.CONDITIONAL);
		ret.register(Pattern.compile("^loop$"), MExpressionTerms.LOOP);
		
		ret.register(Pattern.compile("^\\-?[0-9]+.?[0-9]*$"), (str)->MExpressionTerms.of(Double.parseDouble(str)));
		ret.register(Pattern.compile("^[a-zA-Z0-9\\._]*$"), (str)->MExpressionTerms.of(str));
		ret.register(Pattern.compile("^([\'\"]).*\\1$"), (str)->MExpressionTerms.litteral(str.substring(1, str.length()-1)));
		
		ret.register(Pattern.compile("^Sum:[a-zA-Z]$"), (str)->MExpressionTerms.sum(str.substring(4, 5)));
		ret.register(Pattern.compile("^[a-zA-Z]'$"), (str)->MExpressionTerms.derivative(str.substring(0, str.length()-1)));
		
		ret.register(Pattern.compile("^\\|$"), MExpressionTerms.CONCAT);
		
		
		return ret;
	}
	
	public static MExpression parse(String words[]) throws MExpressionBuilderException,MExpressionSerializerException {
			MExpressionSerializer serializer=createBaseMExpressionSerializer();
			MExpressionBuilder builder=new MExpressionBuilder();
			return builder.compile(serializer.serialize(Arrays.stream(words).iterator()));
	}
}
