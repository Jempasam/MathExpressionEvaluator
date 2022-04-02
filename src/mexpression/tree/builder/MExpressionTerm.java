package mexpression.tree.builder;

import java.util.List;
import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

import mexpression.tree.MExpression;
import mexpression.tree.binary.AdditionMExpression;
import mexpression.tree.binary.DivideMExpression;
import mexpression.tree.binary.MultiplyMExpression;
import mexpression.tree.binary.PowerMExpression;
import mexpression.tree.binary.SubstractMExpression;
import mexpression.tree.simple.NumberMExpression;
import mexpression.tree.simple.ParameterMExpression;
import mexpression.tree.unary.AbsoluteMExpression;
import mexpression.tree.unary.SquareRootMExpression;
import mexpression.tree.unary.UnaryOperatorMExpression;

public interface MExpressionTerm {
	public int[] getArgumentsPlaces();
	public MExpression from(List<MExpression> args);
	public int getPriority();
		
	// Special
	public static MExpressionTerm OPEN=new MExpressionTerm() {
		public int getPriority() { return 0; }
		public int[] getArgumentsPlaces() { return null; }
		public MExpression from(List<MExpression> args) { return null; }
		public String toString() {return "open parenthesis";}
	};
	
	public static MExpressionTerm CLOSE=new MExpressionTerm() {
		public int getPriority() { return 0; }
		public int[] getArgumentsPlaces() { return null; }
		public MExpression from(List<MExpression> args) { return null; }
		public String toString() {return "close parenthesis";}
	};
	
	// Simple Operator
	public static MExpressionTerm of(double v) {
		return new MExpressionTerm() {
			public int getPriority() { return 1000; }
			public int[] getArgumentsPlaces() { return new int[0]; }
			public MExpression from(List<MExpression> args) { return new NumberMExpression(v); }
			public String toString() {return "number "+Double.toString(v);}
		};
	}
	
	public static MExpressionTerm of(String name) {
		return new MExpressionTerm() {
			public int getPriority() { return 1000; }
			public int[] getArgumentsPlaces() { return new int[0]; }
			public MExpression from(List<MExpression> args) { return new ParameterMExpression(name); }
			public String toString() {return "argument "+name;}
		};
	}
	
	public static MExpressionTerm of(DoubleUnaryOperator operator) {
		return new MExpressionTerm() {
			public int getPriority() { return 1000; }
			public int[] getArgumentsPlaces() { return new int[0]; }
			public MExpression from(List<MExpression> args) { return new UnaryOperatorMExpression(args.get(0),operator); }
			public String toString() {return "operator  "+operator.toString();}
		};
	}
	
	
	// Binary Operator
	public static MExpressionTerm MULTIPLY=new MExpressionTerm() {
		public int getPriority() { return 700; }
		public int[] getArgumentsPlaces() { return new int[] {-1,1}; }
		public MExpression from(List<MExpression> args) { return new MultiplyMExpression(args.get(0),args.get(1)); }
		public String toString() {return "multiply";}
	};
	
	public static MExpressionTerm DIVIDE=new MExpressionTerm() {
		public int getPriority() { return 700; }
		public int[] getArgumentsPlaces() { return new int[] {-1,1}; }
		public MExpression from(List<MExpression> args) { return new DivideMExpression(args.get(0),args.get(1)); }
		public String toString() {return "divide";}
	};
	
	public static MExpressionTerm ADD=new MExpressionTerm() {
		public int getPriority() { return 600; }
		public int[] getArgumentsPlaces() { return new int[] {-1,1}; }
		public MExpression from(List<MExpression> args) { return new AdditionMExpression(args.get(0),args.get(1)); }
		public String toString() {return "add";}
	};
	
	public static MExpressionTerm MINUS=new MExpressionTerm() {
		public int getPriority() { return 600; }
		public int[] getArgumentsPlaces() { return new int[] {-1,1}; }
		public MExpression from(List<MExpression> args) { return new SubstractMExpression(args.get(0),args.get(1)); }
		public String toString() {return "minus";}
	};
	
	public static MExpressionTerm POWER=new MExpressionTerm() {
		public int getPriority() { return 900; }
		public int[] getArgumentsPlaces() { return new int[] {-1,1}; }
		public MExpression from(List<MExpression> args) { return new PowerMExpression(args.get(0),args.get(1)); }
		public String toString() {return "power";}
	};
	
	
	// Unary Operator
	public static MExpressionTerm ABSOLUTE=new MExpressionTerm() {
		public int getPriority() { return 800; }
		public int[] getArgumentsPlaces() { return new int[] {1}; }
		public MExpression from(List<MExpression> args) { return new AbsoluteMExpression(args.get(0)); }
		public String toString() {return "absolute";}
	};
	
	public static MExpressionTerm SQRT=new MExpressionTerm() {
		public int getPriority() { return 800; }
		public int[] getArgumentsPlaces() { return new int[] {1}; }
		public MExpression from(List<MExpression> args) { return new SquareRootMExpression(args.get(0)); }
		public String toString() {return "square root";}
	};
}
