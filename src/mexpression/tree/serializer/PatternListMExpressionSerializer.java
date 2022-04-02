package mexpression.tree.serializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;

import mexpression.tree.builder.MExpressionTerm;
import mexpression.tree.serializer.MExpressionSerializer.MExpressionSerializerException;

public class PatternListMExpressionSerializer implements MExpressionSerializer{
	
	
	private List<Pair> registreds;
	
	private class Pair{
		Pattern pattern;
		Function<String,MExpressionTerm> converter;
		
		public Pair(Pattern pattern, Function<String, MExpressionTerm> converter) {
			super();
			this.pattern = pattern;
			this.converter = converter;
		}
		
	}
	
	public PatternListMExpressionSerializer() {
		registreds=new ArrayList<>();
	}
	
	
	public List<MExpressionTerm> serialize(Iterator<String> words) throws MExpressionSerializerException{
		List<MExpressionTerm> ret=new ArrayList<>();
		while(words.hasNext()) {
			String word=words.next();
			Function<String,MExpressionTerm> generator=getgenerator(word);
			if(generator==null)throw new MExpressionSerializerException(word);
			ret.add(generator.apply(word));
		}
		return ret;
	}
	
	private Function<String,MExpressionTerm> getgenerator(String word){
		for(Pair e : registreds) {
			System.out.println("aa");
			System.out.println(e.pattern+":"+word);
			if(e.pattern.matcher(word).find()) {
				return e.converter;
			}
		}
		return null;
	}
	
	public void register(Pattern pattern, Function<String,MExpressionTerm> treatement) {
		registreds.add(new Pair(pattern, treatement));
	}
	
	public void register(Pattern pattern, MExpressionTerm term) {
		registreds.add(new Pair(pattern, (w)->term));
	}
}
