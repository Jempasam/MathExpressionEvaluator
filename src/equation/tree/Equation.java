package equation.tree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import equation.tree.builder.EquationBuilder;
import equation.tree.builder.EquationBuilder.EquationBuilderException;
import equation.tree.builder.EquationTerm;
import equation.tree.serializer.EquationSerializer;
import equation.tree.serializer.EquationSerializer.EquationSerializerException;

public interface Equation {
	
	double get(Map<String, Double> parameters);
	String getVisual();
	List<String> getParameters();
	
	public static void main(String[] args) {
		try {
			// From STR to LIST
			String str[]= {"19","+","26","*","45","/","X"};
			EquationSerializer serializer=EquationSerializer.createBaseEquationSerializer();
			List<EquationTerm> terms=serializer.serialize(Arrays.stream(str).iterator());
			
			System.out.println(terms);
			
			// From LIST to EQUATION
			EquationBuilder builder=new EquationBuilder();
			Equation equation=builder.compile(terms);
			
			// Use EQUATION
			Map<String,Double> parameters=new HashMap<>();
			System.out.println(equation.getParameters());
			System.out.println(equation.getVisual());
			parameters.put("X", 1d);
			System.out.println(equation.get(parameters));
			parameters.put("X", 2d);
			System.out.println(equation.get(parameters));
			parameters.put("X", 4d);
			System.out.println(equation.get(parameters));
			
		}catch(EquationBuilderException e) {
			e.printStackTrace();
		} catch (EquationSerializerException e1) {
			e1.printStackTrace();
		}
	}
	
}
