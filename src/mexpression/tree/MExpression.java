package mexpression.tree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mexpression.tree.builder.MExpressionBuilder;
import mexpression.tree.builder.MExpressionTerm;
import mexpression.tree.builder.MExpressionBuilder.MExpressionBuilderException;
import mexpression.tree.serializer.MExpressionSerializer;
import mexpression.tree.serializer.MExpressionSerializer.MExpressionSerializerException;

public interface MExpression {
	
	double get(Map<String, Double> parameters);
	String getVisual();
	List<String> getParameters();
	
	public static void main(String[] args) {
		try {
			// From STR to LIST
			String str[]= {"19","+","26","*","45","/","X"};
			MExpressionSerializer serializer=MExpressionSerializer.createBaseMExpressionSerializer();
			List<MExpressionTerm> terms=serializer.serialize(Arrays.stream(str).iterator());
			
			System.out.println(terms);
			
			// From LIST to EQUATION
			MExpressionBuilder builder=new MExpressionBuilder();
			MExpression mexpression=builder.compile(terms);
			
			// Use EQUATION
			Map<String,Double> parameters=new HashMap<>();
			System.out.println(mexpression.getParameters());
			System.out.println(mexpression.getVisual());
			parameters.put("X", 1d);
			System.out.println(mexpression.get(parameters));
			parameters.put("X", 2d);
			System.out.println(mexpression.get(parameters));
			parameters.put("X", 4d);
			System.out.println(mexpression.get(parameters));
			
		}catch(MExpressionBuilderException e) {
			e.printStackTrace();
		} catch (MExpressionSerializerException e1) {
			e1.printStackTrace();
		}
	}
	
}
