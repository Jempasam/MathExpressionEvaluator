package jempasam.mexpression.tree.serializer;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import jempasam.mexpression.tree.builder.MExpressionTerm;

public interface MExpressionSerializer {
	public List<MExpressionTerm> serialize(Iterator<String> words)  throws MExpressionSerializerException;
	
	public class MExpressionSerializerException extends Exception{
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
