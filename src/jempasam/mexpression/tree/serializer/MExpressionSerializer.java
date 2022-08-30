package jempasam.mexpression.tree.serializer;

import java.util.Iterator;
import java.util.List;

import jempasam.mexpression.tree.builder.term.MExpressionTerm;

public interface MExpressionSerializer {
	public List<MExpressionTerm> serialize(Iterator<String> words)  throws MExpressionSerializerException;
	
	public class MExpressionSerializerException extends Exception{
		
		private static final long serialVersionUID = 7358731708833265619L;
		private String token;

		public MExpressionSerializerException(String token) {
			super("Invalid Token "+token+".");
			this.token = token;
		}

		public String getToken() {
			return token;
		}
		
	}
}
