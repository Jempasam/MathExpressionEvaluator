package equation.tree.builder;

import java.util.List;
import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

import equation.tree.Equation;
import equation.tree.binary.AdditionEquation;
import equation.tree.binary.DivideEquation;
import equation.tree.binary.MultiplyEquation;
import equation.tree.binary.PowerEquation;
import equation.tree.binary.SubstractEquation;
import equation.tree.simple.NumberEquation;
import equation.tree.simple.ParameterEquation;
import equation.tree.unary.AbsoluteEquation;
import equation.tree.unary.SquareRootEquation;
import equation.tree.unary.UnaryOperatorEquation;

public interface EquationTerm {
	public int[] getArgumentsPlaces();
	public Equation from(List<Equation> args);
	public int getPriority();
		
	// Special
	public static EquationTerm OPEN=new EquationTerm() {
		public int getPriority() { return 0; }
		public int[] getArgumentsPlaces() { return null; }
		public Equation from(List<Equation> args) { return null; }
		public String toString() {return "open parenthesis";}
	};
	
	public static EquationTerm CLOSE=new EquationTerm() {
		public int getPriority() { return 0; }
		public int[] getArgumentsPlaces() { return null; }
		public Equation from(List<Equation> args) { return null; }
		public String toString() {return "close parenthesis";}
	};
	
	// Simple Operator
	public static EquationTerm of(double v) {
		return new EquationTerm() {
			public int getPriority() { return 1000; }
			public int[] getArgumentsPlaces() { return new int[0]; }
			public Equation from(List<Equation> args) { return new NumberEquation(v); }
			public String toString() {return "number "+Double.toString(v);}
		};
	}
	
	public static EquationTerm of(String name) {
		return new EquationTerm() {
			public int getPriority() { return 1000; }
			public int[] getArgumentsPlaces() { return new int[0]; }
			public Equation from(List<Equation> args) { return new ParameterEquation(name); }
			public String toString() {return "argument "+name;}
		};
	}
	
	public static EquationTerm of(DoubleUnaryOperator operator) {
		return new EquationTerm() {
			public int getPriority() { return 1000; }
			public int[] getArgumentsPlaces() { return new int[0]; }
			public Equation from(List<Equation> args) { return new UnaryOperatorEquation(args.get(0),operator); }
			public String toString() {return "operator  "+operator.toString();}
		};
	}
	
	
	// Binary Operator
	public static EquationTerm MULTIPLY=new EquationTerm() {
		public int getPriority() { return 700; }
		public int[] getArgumentsPlaces() { return new int[] {-1,1}; }
		public Equation from(List<Equation> args) { return new MultiplyEquation(args.get(0),args.get(1)); }
		public String toString() {return "multiply";}
	};
	
	public static EquationTerm DIVIDE=new EquationTerm() {
		public int getPriority() { return 700; }
		public int[] getArgumentsPlaces() { return new int[] {-1,1}; }
		public Equation from(List<Equation> args) { return new DivideEquation(args.get(0),args.get(1)); }
		public String toString() {return "divide";}
	};
	
	public static EquationTerm ADD=new EquationTerm() {
		public int getPriority() { return 600; }
		public int[] getArgumentsPlaces() { return new int[] {-1,1}; }
		public Equation from(List<Equation> args) { return new AdditionEquation(args.get(0),args.get(1)); }
		public String toString() {return "add";}
	};
	
	public static EquationTerm MINUS=new EquationTerm() {
		public int getPriority() { return 600; }
		public int[] getArgumentsPlaces() { return new int[] {-1,1}; }
		public Equation from(List<Equation> args) { return new SubstractEquation(args.get(0),args.get(1)); }
		public String toString() {return "minus";}
	};
	
	public static EquationTerm POWER=new EquationTerm() {
		public int getPriority() { return 900; }
		public int[] getArgumentsPlaces() { return new int[] {-1,1}; }
		public Equation from(List<Equation> args) { return new PowerEquation(args.get(0),args.get(1)); }
		public String toString() {return "power";}
	};
	
	
	// Unary Operator
	public static EquationTerm ABSOLUTE=new EquationTerm() {
		public int getPriority() { return 800; }
		public int[] getArgumentsPlaces() { return new int[] {1}; }
		public Equation from(List<Equation> args) { return new AbsoluteEquation(args.get(0)); }
		public String toString() {return "absolute";}
	};
	
	public static EquationTerm SQRT=new EquationTerm() {
		public int getPriority() { return 800; }
		public int[] getArgumentsPlaces() { return new int[] {1}; }
		public Equation from(List<Equation> args) { return new SquareRootEquation(args.get(0)); }
		public String toString() {return "square root";}
	};
}
