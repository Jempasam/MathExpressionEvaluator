package equation.tree.serializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;

import equation.tree.builder.EquationTerm;
import equation.tree.serializer.EquationSerializer.EquationSerializerException;

public class PatternListEquationSerializer implements EquationSerializer{
	
	
	private List<Pair> registreds;
	
	private class Pair{
		Pattern pattern;
		Function<String,EquationTerm> converter;
		
		public Pair(Pattern pattern, Function<String, EquationTerm> converter) {
			super();
			this.pattern = pattern;
			this.converter = converter;
		}
		
	}
	
	public PatternListEquationSerializer() {
		registreds=new ArrayList<>();
	}
	
	
	public List<EquationTerm> serialize(Iterator<String> words) throws EquationSerializerException{
		List<EquationTerm> ret=new ArrayList<>();
		while(words.hasNext()) {
			String word=words.next();
			Function<String,EquationTerm> generator=getgenerator(word);
			if(generator==null)throw new EquationSerializerException(word);
			ret.add(generator.apply(word));
		}
		return ret;
	}
	
	private Function<String,EquationTerm> getgenerator(String word){
		for(Pair e : registreds) {
			System.out.println("aa");
			System.out.println(e.pattern+":"+word);
			if(e.pattern.matcher(word).find()) {
				return e.converter;
			}
		}
		return null;
	}
	
	public void register(Pattern pattern, Function<String,EquationTerm> treatement) {
		registreds.add(new Pair(pattern, treatement));
	}
	
	public void register(Pattern pattern, EquationTerm term) {
		registreds.add(new Pair(pattern, (w)->term));
	}
}
