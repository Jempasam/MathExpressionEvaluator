package jempasam.mexpression.tree.builder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import jempasam.mexpression.tree.MExpression;
import jempasam.mexpression.tree.builder.term.MExpressionTerm;
import jempasam.mexpression.tree.builder.term.MExpressionTermArgument;
import jempasam.mexpression.tree.builder.term.MExpressionTerms;
import jempasam.mexpression.tree.doubletype.DoubleMExpression;

public class MExpressionBuilder {
	
	private Iterable<Integer> range(int min, int max, int direction){
		return new Iterable<Integer>(){
			@Override
			public Iterator<Integer> iterator() {
				return new Iterator<Integer>() {
					 int a;
					 int end;
					 {
						 if(direction==1) { a=min-1; end=max; }
						 else{ a=max+1; end=min;  }
					 }
					public boolean hasNext() { return a!=end; }
					public Integer next() { a+=direction; return a; }
				};
			}
		};
	}
	
	public MExpression<?> compile(List<MExpressionTerm> mexpression) throws MExpressionBuilderException{
		
		List<CompleteTerm> compiled=new ArrayList<>();
		
		// Compile
		int depth=0;
		for(int i=0; i<mexpression.size(); i++) {
			MExpressionTerm e=mexpression.get(i);
			if(e==MExpressionTerms.OPEN)depth++;
			else if(e==MExpressionTerms.CLOSE) {
				if(depth<=0)throw new MExpressionBuilderException("Not opened parenthesis has been closed", i, e);
				depth--;
			}
			else {
				compiled.add(cterm(e, depth, i));
			}
		}
		
		// Create Equation From Compiled
		while(compiled.size()>1 || !compiled.get(0).isResult()) {
			System.out.println(compiled);
			
			// Get max priority and direction: direction ,maxpriority
			int maxpriority=Integer.MIN_VALUE+1;
			int direction=1;
			for(CompleteTerm t : compiled) {
				if(t.priority>maxpriority) {
					maxpriority=t.priority;
					direction=t.term.getDirection();
				}
				else if(t.priority==maxpriority && direction != t.term.getDirection()){
					throw new MExpressionBuilderException("Operator direction incoherence between "+direction+" and "+t.term.getDirection()+" for priority "+maxpriority, t.place, t.term);
				}
			}
			if(maxpriority==Integer.MIN_VALUE+1) {
				throw new MExpressionBuilderException("Invalid Expression, operators are probably missing.", 0, null);
			}
			
			// Get loop information of direction: i, end
			int i=0;
			Supplier<Integer> end;
			if(direction==1) {
				i=0;
				end=()->compiled.size();
			}
			else {
				i=compiled.size()-1;
				end=()->-1;
			}
			
			// Execute EquationTerms
			for(; i!=end.get(); i+=direction) {
				CompleteTerm term=compiled.get(i);
				
				// Only the max priority
				if(term.priority!=maxpriority)continue;
				
				// Get arguments
				MExpressionTermArgument[] arguments=term.term.getArgumentsPlaces();
				
				List<MExpression<?>> argvalues=new ArrayList<>();
				List<Integer> arguments_place=new ArrayList<>(arguments.length+1);
				for(int y=0; y<arguments.length; y++) {
					int arg_place=arguments[y].getPos()+i;
					if(arg_place>=0 && arg_place<compiled.size()) {
						CompleteTerm argterm=compiled.get(arg_place);
						if(argterm.isResult()) {
							if(arguments[y].getType().isAssignableFrom(argterm.result.getClass())) {
								argvalues.add(argterm.result);
								arguments_place.add(arg_place);
							}
							else if(arguments[y].isRequired())throw new MExpressionBuilderException("Bad argument type around an operator", term.place, term.term);
						}
						else if(arguments[y].isRequired())throw new MExpressionBuilderException("Not enough arguments around an operator", term.place, term.term);
					}
					else if(arguments[y].isRequired())throw new MExpressionBuilderException("Not enough arguments around an operator", term.place, term.term);
				}
				
				// Get result
				MExpression<?> result=term.term.from(argvalues);
				
				// Replace in compiled
				arguments_place.add(i);
				arguments_place.sort(Integer::compare);
				for(int y=arguments_place.size()-1; y>=0; y--) compiled.remove(arguments_place.get(y).intValue());
				compiled.add(arguments_place.get(0), cterm(result));
				if(direction==1)i-=arguments_place.size()-1;
			}
		}
		return compiled.get(0).result;
	}
	
	private class CompleteTerm{
		public int priority;
		public int place;
		public MExpressionTerm term;
		public MExpression result;
		public CompleteTerm(int priority, int place, MExpressionTerm term, MExpression result) {
			super();
			this.priority = priority;
			this.place = place;
			this.term = term;
			this.result = result;
		}
		public boolean isResult() {
			return result!=null;
		}
		@Override
		public String toString() {
			if(result==null)return "["+term+";"+priority+"]";
			else return "("+result.getVisual()+";"+priority+")";
		}
	}
	
	private CompleteTerm cterm(MExpressionTerm term, int depth, int place) {
		return new CompleteTerm(term.getPriority()+depth*100000, place, term, null);
	}
	
	private CompleteTerm cterm(MExpression mexpression) {
		return new CompleteTerm(Integer.MIN_VALUE, 0, null, mexpression);
	}
	
	public class MExpressionBuilderException extends Exception{
		
		private static final long serialVersionUID = -8767495583232389110L;
		private int position;
		private MExpressionTerm term;
		
		public MExpressionBuilderException(String message, int position, MExpressionTerm term) {
			super("At "+position+" term \""+term+"\": "+message);
			this.position=position;
			this.term=term;
		}
		
		public int getPosition() {
			return position;
		}

		public MExpressionTerm getTerm() {
			return term;
		}
		
	}
	
}
