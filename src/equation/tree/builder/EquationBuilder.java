package equation.tree.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import equation.tree.Equation;

public class EquationBuilder {
	
	public Equation compile(List<EquationTerm> equation) throws EquationBuilderException{
		List<CompleteTerm> compiled=new ArrayList<>();
		
		// Compile
		int depth=0;
		for(int i=0; i<equation.size(); i++) {
			EquationTerm e=equation.get(i);
			if(e==EquationTerm.OPEN)depth++;
			else if(e==EquationTerm.CLOSE) {
				if(depth<=0)throw new EquationBuilderException("Not opened parenthesis has been closed", i, e);
				depth--;
			}
			else {
				compiled.add(cterm(e, depth, i));
			}
		}
		
		// Create Equation From Compiled
		while(compiled.size()>1 || !compiled.get(0).isResult()) {
			// Get max priority
			int maxpriority=0;
			for(CompleteTerm t : compiled) {
				if(t.priority>maxpriority)maxpriority=t.priority;
			}
			// Execute EquationTerms
			for(int i=0; i<compiled.size(); i++) {
				CompleteTerm term=compiled.get(i);
				
				// Only the max priority
				if(term.priority!=maxpriority)continue;
				
				// Get arguments
				int arg_offset[]=term.term.getArgumentsPlaces();
				int arg_pos[]=new int[arg_offset.length+1];
				for(int y=0; y<arg_offset.length; y++)arg_pos[y]=arg_offset[y]+i;
				List<Equation> arguments=new ArrayList<>();
				for(int y=0; y<arg_offset.length; y++) {
					if(arg_pos[y]<0 || arg_pos[y]>=compiled.size() || !compiled.get(arg_pos[y]).isResult())
						throw new EquationBuilderException("Miss an argument around an operator", term.place, term.term);
					else {
						arguments.add(compiled.get(arg_pos[y]).result);
					}
				}
				
				// Get result
				Equation result=term.term.from(arguments);
				
				// Replace in compiled
				arg_pos[arg_pos.length-1]=i;

				Arrays.sort(arg_pos);
				for(int y=arg_pos.length-1; y>=0; y--) compiled.remove(arg_pos[y]);
				compiled.add(arg_pos[0], cterm(result));
				i-=arg_offset.length;
			}
		}
		return compiled.get(0).result;
	}
	
	private class CompleteTerm{
		public int priority;
		public int place;
		public EquationTerm term;
		public Equation result;
		public CompleteTerm(int priority, int place, EquationTerm term, Equation result) {
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
	private CompleteTerm cterm(EquationTerm term, int depth, int place) {
		return new CompleteTerm(term.getPriority()+depth*100000, place, term, null);
	}
	
	private CompleteTerm cterm(Equation equation) {
		return new CompleteTerm(0, 0, null, equation);
	}
	
	
	public class EquationBuilderException extends Exception{
		
		private static final long serialVersionUID = -8767495583232389110L;
		private int position;
		private EquationTerm term;
		
		public EquationBuilderException(String message, int position, EquationTerm term) {
			super(message);
			this.position=position;
			this.term=term;
		}
		
		public int getPosition() {
			return position;
		}

		public EquationTerm getTerm() {
			return term;
		}
		
	}
	
}
