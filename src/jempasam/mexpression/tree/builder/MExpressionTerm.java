package jempasam.mexpression.tree.builder;

import java.util.List;
import java.util.function.DoubleUnaryOperator;
import jempasam.mexpression.tree.MExpression;
import jempasam.mexpression.tree.binary.*;
import jempasam.mexpression.tree.bool.*;
import jempasam.mexpression.tree.simple.*;
import jempasam.mexpression.tree.unary.*;

public interface MExpressionTerm {
	public int[] getArgumentsPlaces();
	public MExpression from(List<MExpression> args);
	public int getPriority();
	public int getDirection();
		
	// Special
	public static MExpressionTerm OPEN=new MExpressionTerm() {
		public int getPriority() { return 0; }
		public int getDirection() { return 0; }
		public int[] getArgumentsPlaces() { return null; }
		public MExpression from(List<MExpression> args) { return null; }
		public String toString() {return "open parenthesis";}
	};
	
	public static MExpressionTerm CLOSE=new MExpressionTerm() {
		public int getPriority() { return 0; }
		public int getDirection() { return 0; }
		public int[] getArgumentsPlaces() { return null; }
		public MExpression from(List<MExpression> args) { return null; }
		public String toString() {return "close parenthesis";}
	};
	
	// Simple Operator
	public static MExpressionTerm derivative(String param) {
		return new MExpressionTerm() {
			public int getPriority() { return 1000; }
			public int getDirection() { return 1; }
			public int[] getArgumentsPlaces() { return new int[] {-1}; }
			public MExpression from(List<MExpression> args) { return new DerivativeMExpression(args.get(0),param); }
			public String toString() {return "derivative with respect to "+param;}
		};
	}
	
	public static MExpressionTerm of(double v) {
		return new MExpressionTerm() {
			public int getPriority() { return 1000; }
			public int getDirection() { return 1; }
			public int[] getArgumentsPlaces() { return new int[0]; }
			public MExpression from(List<MExpression> args) { return new NumberMExpression(v); }
			public String toString() {return "number "+Double.toString(v);}
		};
	}
	
	public static MExpressionTerm PI=of(Math.PI);
	
	public static MExpressionTerm E=of(Math.E);
	
	public static MExpressionTerm TRUE=of(1d);
	
	public static MExpressionTerm FALSE=of(0d);
	
	
	public static MExpressionTerm of(String name) {
		return new MExpressionTerm() {
			public int getPriority() { return 1000; }
			public int getDirection() { return 1; }
			public int[] getArgumentsPlaces() { return new int[0]; }
			public MExpression from(List<MExpression> args) { return new ParameterMExpression(name); }
			public String toString() {return "argument "+name;}
		};
	}
	
	public static MExpressionTerm of(DoubleUnaryOperator operator) {
		return new MExpressionTerm() {
			public int getPriority() { return 800; }
			public int getDirection() { return -1; }
			public int[] getArgumentsPlaces() { return new int[]{1}; }
			public MExpression from(List<MExpression> args) { return new UnaryOperatorMExpression(args.get(0),operator); }
			public String toString() {return "operator  "+operator.toString();}
		};
	}
	
	public static MExpressionTerm COS=of(Math::cos);
	public static MExpressionTerm ARCCOS=of(Math::acos);
	
	public static MExpressionTerm SIN=of(Math::sin);
	public static MExpressionTerm ARCSIN=of(Math::asin);
	
	public static MExpressionTerm TAN=of(Math::tan);
	public static MExpressionTerm ARCTAN=of(Math::atan);
	
	public static MExpressionTerm EXP=of(Math::exp);
	public static MExpressionTerm LOG=of(Math::log);
	
	public static MExpressionTerm FLOOR=of(Math::floor);
	public static MExpressionTerm CEIL=of(Math::ceil);
	
	public static MExpressionTerm LOG10=of(Math::log10);
	
	
	// Binary Operator
	public static MExpressionTerm MULTIPLY=new MExpressionTerm() {
		public int getPriority() { return 700; }
		public int getDirection() { return 1; }
		public int[] getArgumentsPlaces() { return new int[] {-1,1}; }
		public MExpression from(List<MExpression> args) { return new MultiplyMExpression(args.get(0),args.get(1)); }
		public String toString() {return "multiply";}
	};
	
	public static MExpressionTerm DIVIDE=new MExpressionTerm() {
		public int getPriority() { return 700; }
		public int getDirection() { return 1; }
		public int[] getArgumentsPlaces() { return new int[] {-1,1}; }
		public MExpression from(List<MExpression> args) { return new DivideMExpression(args.get(0),args.get(1)); }
		public String toString() {return "divide";}
	};
	
	public static MExpressionTerm ADD=new MExpressionTerm() {
		public int getPriority() { return 600; }
		public int getDirection() { return 1; }
		public int[] getArgumentsPlaces() { return new int[] {-1,1}; }
		public MExpression from(List<MExpression> args) { return new AdditionMExpression(args.get(0),args.get(1)); }
		public String toString() {return "add";}
	};
	
	public static MExpressionTerm MINUS=new MExpressionTerm() {
		public int getPriority() { return 600; }
		public int getDirection() { return 1; }
		public int[] getArgumentsPlaces() { return new int[] {-1,1}; }
		public MExpression from(List<MExpression> args) { return new SubstractMExpression(args.get(0),args.get(1)); }
		public String toString() {return "minus";}
	};
	
	public static MExpressionTerm POWER=new MExpressionTerm() {
		public int getPriority() { return 900; }
		public int getDirection() { return -1; }
		public int[] getArgumentsPlaces() { return new int[] {-1,1}; }
		public MExpression from(List<MExpression> args) { return new PowerMExpression(args.get(0),args.get(1)); }
		public String toString() {return "power";}
	};
	
	public static MExpressionTerm MAX=new MExpressionTerm() {
		public int getPriority() { return 800; }
		public int getDirection() { return -1; }
		public int[] getArgumentsPlaces() { return new int[] {1,2}; }
		public MExpression from(List<MExpression> args) { return new MaxMExpression(args.get(0),args.get(1)); }
		public String toString() {return "max";}
	};
	
	public static MExpressionTerm MIN=new MExpressionTerm() {
		public int getPriority() { return 800; }
		public int getDirection() { return -1; }
		public int[] getArgumentsPlaces() { return new int[] {1,2}; }
		public MExpression from(List<MExpression> args) { return new MinMExpression(args.get(0),args.get(1)); }
		public String toString() {return "min";}
	};
	
	public static MExpressionTerm MIDDLE=new MExpressionTerm() {
		public int getPriority() { return 800; }
		public int getDirection() { return -1; }
		public int[] getArgumentsPlaces() { return new int[] {1,2}; }
		public MExpression from(List<MExpression> args) { return new MiddleMExpression(args.get(0),args.get(1)); }
		public String toString() {return "mid";}
	};
	
	// Unary Operator
	public static MExpressionTerm FACTORIAL=new MExpressionTerm() {
		public int getPriority() { return 850; }
		public int getDirection() { return 1; }
		public int[] getArgumentsPlaces() { return new int[] {-1}; }
		public MExpression from(List<MExpression> args) { return new FactorialMExpression(args.get(0)); }
		public String toString() {return "!";}
	};
	
	public static MExpressionTerm ABSOLUTE=new MExpressionTerm() {
		public int getPriority() { return 800; }
		public int getDirection() { return -1; }
		public int[] getArgumentsPlaces() { return new int[] {1}; }
		public MExpression from(List<MExpression> args) { return new AbsoluteMExpression(args.get(0)); }
		public String toString() {return "absolute";}
	};
	
	public static MExpressionTerm SQRT=new MExpressionTerm() {
		public int getPriority() { return 800; }
		public int getDirection() { return -1; }
		public int[] getArgumentsPlaces() { return new int[] {1}; }
		public MExpression from(List<MExpression> args) { return new SquareRootMExpression(args.get(0)); }
		public String toString() {return "square root";}
	};
	
	// Comparator Operator
	public static MExpressionTerm GREATER_THAN=new MExpressionTerm() {
		public int getPriority() { return 500; }
		public int getDirection() { return 1; }
		public int[] getArgumentsPlaces() { return new int[] {-1,1}; }
		public MExpression from(List<MExpression> args) { return new GreaterThanMExpression(args.get(0),args.get(1)); }
		public String toString() {return "greater than";}
	};
	
	public static MExpressionTerm LOWER_THAN=new MExpressionTerm() {
		public int getPriority() { return 500; }
		public int getDirection() { return 1; }
		public int[] getArgumentsPlaces() { return new int[] {-1,1}; }
		public MExpression from(List<MExpression> args) { return new LowerThanMExpression(args.get(0),args.get(1)); }
		public String toString() {return "lower than";}
	};
	
	public static MExpressionTerm EQUALS=new MExpressionTerm() {
		public int getPriority() { return 500; }
		public int getDirection() { return 1; }
		public int[] getArgumentsPlaces() { return new int[] {-1,1}; }
		public MExpression from(List<MExpression> args) { return new EqualMExpression(args.get(0),args.get(1)); }
		public String toString() {return "equal";}
	};
	
	
	// Boolean Operator
	public static MExpressionTerm AND=new MExpressionTerm() {
		public int getPriority() { return 300; }
		public int getDirection() { return 1; }
		public int[] getArgumentsPlaces() { return new int[] {-1,1}; }
		public MExpression from(List<MExpression> args) { return new AndMExpression(args.get(0),args.get(1)); }
		public String toString() {return "and";}
	};
	
	public static MExpressionTerm OR=new MExpressionTerm() {
		public int getPriority() { return 200; }
		public int getDirection() { return 1; }
		public int[] getArgumentsPlaces() { return new int[] {-1,1}; }
		public MExpression from(List<MExpression> args) { return new OrMExpression(args.get(0),args.get(1)); }
		public String toString() {return "or";}
	};
	
	public static MExpressionTerm NOT=new MExpressionTerm() {
		public int getPriority() { return 400; }
		public int getDirection() { return -1; }
		public int[] getArgumentsPlaces() { return new int[] {1}; }
		public MExpression from(List<MExpression> args) { return new NotMExpression(args.get(0)); }
		public String toString() {return "not";}
	};
}
