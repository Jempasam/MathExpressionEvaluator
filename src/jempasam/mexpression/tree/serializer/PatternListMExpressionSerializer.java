package jempasam.mexpression.tree.serializer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;

import jempasam.mexpression.tree.builder.term.MExpressionTerm;
import jempasam.mexpression.tree.serializer.MExpressionSerializer.MExpressionSerializerException;

public class PatternListMExpressionSerializer implements MExpressionSerializer{
	
	
	private List<Pair> registreds;
	
	private class Pair{
		Pattern pattern;
		Function<String,List<MExpressionTerm>> converter;
		
		public Pair(Pattern pattern, Function<String, List<MExpressionTerm>> converter) {
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
			Function<String,List<MExpressionTerm>> generator=getgenerator(word);
			if(generator==null)throw new MExpressionSerializerException(word);
			ret.addAll(generator.apply(word));
		}
		return ret;
	}
	
	private Function<String,List<MExpressionTerm>> getgenerator(String word){
		for(Pair e : registreds) {
			if(e.pattern.matcher(word).find()) {
				return e.converter;
			}
		}
		return null;
	}
	
	public void registerTList(Pattern pattern, Function<String,List<MExpressionTerm>> treatement) {
		registreds.add(new Pair(pattern, treatement));
	}
	
	public void register(Pattern pattern, Function<String,MExpressionTerm> treatement) {
		registreds.add(new Pair(pattern, (str)->Arrays.asList(treatement.apply(str))));
	}
	
	public void register(Pattern pattern, List<MExpressionTerm> term) {
		registreds.add(new Pair(pattern, (w)->term));
	}
	
	public void register(Pattern pattern, MExpressionTerm term) {
		registreds.add(new Pair(pattern, (w)->Arrays.asList(term)));
	}
}
