package equation.tree.serializer;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import equation.tree.builder.EquationTerm;

public interface EquationSerializer {
	public List<EquationTerm> serialize(Iterator<String> words)  throws EquationSerializerException;
	
	public class EquationSerializerException extends Exception{
		private String token;

		public EquationSerializerException(String token) {
			super("Invalid Token "+token+".");
			this.token = token;
		}

		public String getToken() {
			return token;
		}
		
	}
	
	public static PatternListEquationSerializer createBaseEquationSerializer() {
		PatternListEquationSerializer ret=new PatternListEquationSerializer();
		ret.register(Pattern.compile("\\+"), EquationTerm.ADD);
		ret.register(Pattern.compile("\\-"), EquationTerm.MINUS);
		ret.register(Pattern.compile("\\*"), EquationTerm.MULTIPLY);
		ret.register(Pattern.compile("\\/"), EquationTerm.DIVIDE);
		ret.register(Pattern.compile("\\^"), EquationTerm.POWER);
		
		ret.register(Pattern.compile("abs"), EquationTerm.ABSOLUTE);
		ret.register(Pattern.compile("sqrt"), EquationTerm.SQRT);
		
		ret.register(Pattern.compile("\\("), EquationTerm.OPEN);
		ret.register(Pattern.compile("\\)"), EquationTerm.CLOSE);
		
		ret.register(Pattern.compile("\\-?[1-9]+.?[1-9]*"), (str)->EquationTerm.of(Double.parseDouble(str)));
		ret.register(Pattern.compile("[A-Z]"), (str)->EquationTerm.of(str));
		
		return ret;
	}
}
