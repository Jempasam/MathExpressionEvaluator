package jempasam.mexpression.tree.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

import jempasam.mexpression.tree.MExpression;

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
	
	public MExpression compile(List<MExpressionTerm> mexpression) throws MExpressionBuilderException{
		List<CompleteTerm> compiled=new ArrayList<>();
		// Compile
		int depth=0;
		for(int i=0; i<mexpression.size(); i++) {
			MExpressionTerm e=mexpression.get(i);
			if(e==MExpressionTerm.OPEN)depth++;
			else if(e==MExpressionTerm.CLOSE) {
				if(depth<=0)throw new MExpressionBuilderException("Not opened parenthesis has been closed", i, e);
				depth--;
			}
			else {
				compiled.add(cterm(e, depth, i));
			}
		}
		
		// Create Equation From Compiled
		while(compiled.size()>1 || !compiled.get(0).isResult()) {
			// Get max priority and direction
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
			
			// Execute EquationTerms
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
			for(; i!=end.get(); i+=direction) {
				CompleteTerm term=compiled.get(i);
				
				// Only the max priority
				if(term.priority!=maxpriority)continue;
				
				// Get arguments
				int arg_offset[]=term.term.getArgumentsPlaces();
				int arg_pos[]=new int[arg_offset.length+1];
				for(int y=0; y<arg_offset.length; y++)arg_pos[y]=arg_offset[y]+i;
				List<MExpression> arguments=new ArrayList<>();
				for(int y=0; y<arg_offset.length; y++) {
					if(arg_pos[y]<0 || arg_pos[y]>=compiled.size() || !compiled.get(arg_pos[y]).isResult())
						throw new MExpressionBuilderException("Miss an argument around an operator", term.place, term.term);
					else {
						arguments.add(compiled.get(arg_pos[y]).result);
					}
				}
				
				// Get result
				MExpression result=term.term.from(arguments);
				
				// Replace in compiled
				arg_pos[arg_pos.length-1]=i;

				Arrays.sort(arg_pos);
				for(int y=arg_pos.length-1; y>=0; y--) compiled.remove(arg_pos[y]);
				compiled.add(arg_pos[0], cterm(result));
				if(direction==1)i-=arg_offset.length;
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
			return "("+term+":"+result+":"+priority+")";
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
