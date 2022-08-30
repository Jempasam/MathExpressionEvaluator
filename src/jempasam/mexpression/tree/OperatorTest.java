package jempasam.mexpression.tree;
import java.util.HashMap;
import java.util.List;

import jempasam.mexpression.tree.builder.MExpressionBuilder;
import jempasam.mexpression.tree.builder.MExpressionBuilder.MExpressionBuilderException;
import jempasam.mexpression.tree.builder.term.MExpressionTerm;
import jempasam.mexpression.tree.serializer.MExpressionSerializer;
import jempasam.mexpression.tree.serializer.MExpressionSerializer.MExpressionSerializerException;
import jempasam.samstream.collectors.SamCollectors;
import jempasam.samstream.stream.SamStream;
import jempasam.samstream.text.TokenizerConfig;
import jempasam.samstream.text.TokenizerSStream;

public class OperatorTest {
	public static void main(String[] args) {
		// Tools
		TokenizerConfig tconfig=new TokenizerConfig();
		tconfig.cutChars=" \t\n\r";
		tconfig.commentChars="#";
		tconfig.uniqueChars="()+-/*^%!><?{}|";
		
		MExpressionBuilder builder=new MExpressionBuilder();
		
		MExpressionSerializer serializer=MExpressions.createBaseMExpressionSerializer();
		
		
		// Data
		String str="str(100+X)|'salade'";
		HashMap<MExpressionParameter<?>, Object> parameters=new HashMap<>();
		parameters.put(new MExpressionParameter<>(Double.class, "X"), 10d);
		
		
		//Use
		System.out.println(str);
		
		SamStream<String> tokenized=new TokenizerSStream(str, tconfig);
		//System.out.println("["+tokenized.collect(SamCollectors.concatenate("]["))+"]");
		
		try {
			List<MExpressionTerm> terms = serializer.serialize(tokenized.iterator());
			System.out.println(terms);
			
			MExpression<?> result=builder.compile(terms);
			System.out.println(result.getClass().getSimpleName()+" "+result.getVisual()+" "+result.get(parameters));
			
		} catch (MExpressionBuilderException|MExpressionSerializerException e) {
			e.printStackTrace();
		}
	}
}
