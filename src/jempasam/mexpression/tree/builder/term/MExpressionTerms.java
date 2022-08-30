package jempasam.mexpression.tree.builder.term;

import java.util.List;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;

import jempasam.mexpression.tree.MExpression;
import jempasam.mexpression.tree.booltype.BooleanMExpression;
import jempasam.mexpression.tree.booltype.binary.AndMExpression;
import jempasam.mexpression.tree.booltype.binary.OrMExpression;
import jempasam.mexpression.tree.booltype.numbercomparator.EqualMExpression;
import jempasam.mexpression.tree.booltype.numbercomparator.GreaterThanMExpression;
import jempasam.mexpression.tree.booltype.numbercomparator.LowerThanMExpression;
import jempasam.mexpression.tree.booltype.unary.NotMExpression;
import jempasam.mexpression.tree.doubletype.DoubleMExpression;
import jempasam.mexpression.tree.doubletype.binary.AdditionMExpression;
import jempasam.mexpression.tree.doubletype.binary.BinaryOperatorMExpression;
import jempasam.mexpression.tree.doubletype.binary.DivideMExpression;
import jempasam.mexpression.tree.doubletype.binary.MaxMExpression;
import jempasam.mexpression.tree.doubletype.binary.MiddleMExpression;
import jempasam.mexpression.tree.doubletype.binary.MinMExpression;
import jempasam.mexpression.tree.doubletype.binary.ModuloMExpression;
import jempasam.mexpression.tree.doubletype.binary.MultiplyMExpression;
import jempasam.mexpression.tree.doubletype.binary.PowerMExpression;
import jempasam.mexpression.tree.doubletype.binary.SubstractMExpression;
import jempasam.mexpression.tree.doubletype.other.SumMExpression;
import jempasam.mexpression.tree.doubletype.simple.NumberMExpression;
import jempasam.mexpression.tree.doubletype.simple.DoubleParameterMExpression;
import jempasam.mexpression.tree.doubletype.unary.AbsoluteMExpression;
import jempasam.mexpression.tree.doubletype.unary.DerivativeMExpression;
import jempasam.mexpression.tree.doubletype.unary.FactorialMExpression;
import jempasam.mexpression.tree.doubletype.unary.SquareRootMExpression;
import jempasam.mexpression.tree.doubletype.unary.UnaryOperatorMExpression;
import jempasam.mexpression.tree.logical.ConditionnalMExpression;
import jempasam.mexpression.tree.logical.LoopMExpression;
import jempasam.mexpression.tree.logical.MultipleMExpression;
import jempasam.mexpression.tree.stringtype.StringMExpression;
import jempasam.mexpression.tree.stringtype.binary.ConcatMExpression;
import jempasam.mexpression.tree.stringtype.simple.StringLitteralMExpression;
import jempasam.mexpression.tree.stringtype.unary.ToStringMExpression;

public class MExpressionTerms {
	
	private MExpressionTerms() {}
	
	private static MExpressionTermArgument[] args(MExpressionTermArgument ...args) {
		return args;
	}
	
	private static <T> MExpressionTermArgument[] args(Class<T> type, int ...poses) {
		MExpressionTermArgument[] args=new MExpressionTermArgument[poses.length];
		for(int i=0; i<args.length; i++)args[i]=arg(poses[i], type, true);
		return args;
	}
	
	private static MExpressionTermArgument arg(int pos, Class<?> type, boolean required) {
		return new MExpressionTermArgument(pos, type, required);
	}
	
	private static MExpressionTermArgument[] empty() {
		return new MExpressionTermArgument[0];
	}
	
	private static MExpressionTermArgument req(int pos, Class<?> type) {
		return new MExpressionTermArgument(pos, type, true);
	}
	
	
	// Special
	public static final MExpressionTerm OPEN=new MExpressionTerm() {
		public int getPriority() { return 0; }
		public int getDirection() { return 0; }
		@Override public MExpressionTermArgument[] getArgumentsPlaces() { return empty(); }
		public MExpression<?> from(List<MExpression<?>> args) { return null; }
		public String toString() {return "open parenthesis";}
	};
	
	public static final MExpressionTerm CLOSE=new MExpressionTerm() {
		public int getPriority() { return 0; }
		public int getDirection() { return 0; }
		@Override public MExpressionTermArgument[] getArgumentsPlaces() { return empty(); }
		public MExpression<?> from(List<MExpression<?>> args) { return null; }
		public String toString() {return "close parenthesis";}
	};
	
	
	// Simple Operator
	public static final MExpressionTerm derivative(String param) {
		return new MExpressionTerm() {
			public int getPriority() { return 1000; }
			public int getDirection() { return 1; }
			@Override public MExpressionTermArgument[] getArgumentsPlaces() { return args(req(-1, DoubleMExpression.class)); }
			public MExpression<?> from(List<MExpression<?>> args) { return new DerivativeMExpression((DoubleMExpression)args.get(0),param); }
			public String toString() {return "derivative with respect to "+param;}
		};
	}
	
	public static final MExpressionTerm sum(String param) {
		return new MExpressionTerm() {
			public int getPriority() { return 550; }
			public int getDirection() { return -1; }
			@Override public MExpressionTermArgument[] getArgumentsPlaces() { return args(DoubleMExpression.class, 1, 2, 3); }
			public MExpression<?> from(List<MExpression<?>> args) { return new SumMExpression(param, (DoubleMExpression)args.get(0), (DoubleMExpression)args.get(1), (DoubleMExpression)args.get(2)); }
			public String toString() {return "sum with "+param;}
		};
	}
	
	public static final MExpressionTerm of(double v) {
		return new MExpressionTerm() {
			public int getPriority() { return 1000; }
			public int getDirection() { return 1; }
			@Override public MExpressionTermArgument[] getArgumentsPlaces() { return empty(); }
			public MExpression<?> from(List<MExpression<?>> args) { return new NumberMExpression(v); }
			public String toString() {return "number "+Double.toString(v);}
		};
	}
	public static final MExpressionTerm PI=of(Math.PI);
	public static final MExpressionTerm E=of(Math.E);
	public static final MExpressionTerm TRUE=of(1d);
	public static final MExpressionTerm FALSE=of(0d);
	
	
	public static final MExpressionTerm of(String name) {
		return new MExpressionTerm() {
			public int getPriority() { return 1000; }
			public int getDirection() { return 1; }
			@Override public MExpressionTermArgument[] getArgumentsPlaces() { return empty(); }
			public MExpression<?> from(List<MExpression<?>> args) { return new DoubleParameterMExpression(name); }
			public String toString() {return "argument "+name;}
		};
	}
	
	public static final MExpressionTerm of(DoubleUnaryOperator operator, String visual) {
		return new MExpressionTerm() {
			public int getPriority() { return 800; }
			public int getDirection() { return -1; }
			@Override public MExpressionTermArgument[] getArgumentsPlaces() { return args(DoubleMExpression.class, 1); }
			public MExpression<?> from(List<MExpression<?>> args) { return new UnaryOperatorMExpression((DoubleMExpression)args.get(0),operator,visual); }
			public String toString() {return operator.toString();}
		};
	}
	
	public static final MExpressionTerm of(DoubleUnaryOperator operator) {
		return of(operator,operator.getClass().getSimpleName());
	}
	
	public static final MExpressionTerm COS=of(Math::cos,"cos");
	public static final MExpressionTerm ARCCOS=of(Math::acos,"arccos");
	public static final MExpressionTerm SIN=of(Math::sin,"sin");
	public static final MExpressionTerm ARCSIN=of(Math::asin,"arcsin");
	public static final MExpressionTerm TAN=of(Math::tan,"tan");
	public static final MExpressionTerm ARCTAN=of(Math::atan,"arctan");
	public static final MExpressionTerm EXP=of(Math::exp,"exp");
	public static final MExpressionTerm LOG=of(Math::log,"log");
	public static final MExpressionTerm FLOOR=of(Math::floor,"floor");
	public static final MExpressionTerm CEIL=of(Math::ceil,"ceil");
	public static final MExpressionTerm LOG10=of(Math::log10,"log10");
	
	public static final MExpressionTerm of(DoubleBinaryOperator operator, String visual) {
		return new MExpressionTerm() {
			public int getPriority() { return 800; }
			public int getDirection() { return -1; }
			@Override public MExpressionTermArgument[] getArgumentsPlaces() { return args(DoubleMExpression.class, 1); }
			public MExpression<?> from(List<MExpression<?>> args) { return new BinaryOperatorMExpression((DoubleMExpression)args.get(0),(DoubleMExpression)args.get(1),operator, visual); }
			public String toString() {return operator.toString();}
		};
	}
	
	public static final MExpressionTerm of(DoubleBinaryOperator operator) {
		return of(operator," "+operator.getClass().getSimpleName()+" ");
	}
	
	// Binary Operator
	public static final MExpressionTerm MULTIPLY=new MExpressionTerm() {
		public int getPriority() { return 700; }
		public int getDirection() { return 1; }
		@Override public MExpressionTermArgument[] getArgumentsPlaces() { return args(DoubleMExpression.class, -1, 1); }
		public MExpression<?> from(List<MExpression<?>> args) { return new MultiplyMExpression((DoubleMExpression)args.get(0),(DoubleMExpression)args.get(1)); }
		public String toString() {return "multiply";}
	};
	public static final MExpressionTerm MODULO=new MExpressionTerm() {
		public int getPriority() { return 700; }
		public int getDirection() { return 1; }
		@Override public MExpressionTermArgument[] getArgumentsPlaces() { return args(DoubleMExpression.class, -1, 1); }
		public MExpression<?> from(List<MExpression<?>> args) { return new ModuloMExpression((DoubleMExpression)args.get(0),(DoubleMExpression)args.get(1)); }
		public String toString() {return "multiply";}
	};
	
	public static final MExpressionTerm DIVIDE=new MExpressionTerm() {
		public int getPriority() { return 700; }
		public int getDirection() { return 1; }
		@Override public MExpressionTermArgument[] getArgumentsPlaces() { return args(DoubleMExpression.class, -1, 1); }
		public MExpression<?> from(List<MExpression<?>> args) { return new DivideMExpression((DoubleMExpression)args.get(0),(DoubleMExpression)args.get(1)); }
		public String toString() {return "divide";}
	};
	
	public static final MExpressionTerm ADD=new MExpressionTerm() {
		public int getPriority() { return 600; }
		public int getDirection() { return 1; }
		@Override public MExpressionTermArgument[] getArgumentsPlaces() { return args(DoubleMExpression.class, -1, 1); }
		public MExpression<?> from(List<MExpression<?>> args) { return new AdditionMExpression((DoubleMExpression)args.get(0),(DoubleMExpression)args.get(1)); }
		public String toString() {return "add";}
	};
	
	public static final MExpressionTerm MINUS=new MExpressionTerm() {
		public int getPriority() { return 600; }
		public int getDirection() { return 1; }
		@Override public MExpressionTermArgument[] getArgumentsPlaces() { return args(DoubleMExpression.class, -1, 1); }
		public MExpression<?> from(List<MExpression<?>> args) { return new SubstractMExpression((DoubleMExpression)args.get(0),(DoubleMExpression)args.get(1)); }
		public String toString() {return "minus";}
	};
	
	public static final MExpressionTerm POWER=new MExpressionTerm() {
		public int getPriority() { return 900; }
		public int getDirection() { return -1; }
		@Override public MExpressionTermArgument[] getArgumentsPlaces() { return args(DoubleMExpression.class, -1, 1); }
		public MExpression<?> from(List<MExpression<?>> args) { return new PowerMExpression((DoubleMExpression)args.get(0),(DoubleMExpression)args.get(1)); }
		public String toString() {return "power";}
	};
	
	public static final MExpressionTerm MAX=new MExpressionTerm() {
		public int getPriority() { return 800; }
		public int getDirection() { return -1; }
		@Override public MExpressionTermArgument[] getArgumentsPlaces() { return args(DoubleMExpression.class, 1, 2); }
		public MExpression<?> from(List<MExpression<?>> args) { return new MaxMExpression((DoubleMExpression)args.get(0),(DoubleMExpression)args.get(1)); }
		public String toString() {return "max";}
	};
	
	public static final MExpressionTerm MIN=new MExpressionTerm() {
		public int getPriority() { return 800; }
		public int getDirection() { return -1; }
		@Override public MExpressionTermArgument[] getArgumentsPlaces() { return args(DoubleMExpression.class, 1, 2); }
		public MExpression<?> from(List<MExpression<?>> args) { return new MinMExpression((DoubleMExpression)args.get(0),(DoubleMExpression)args.get(1)); }
		public String toString() {return "min";}
	};
	
	public static final MExpressionTerm MIDDLE=new MExpressionTerm() {
		public int getPriority() { return 800; }
		public int getDirection() { return -1; }
		@Override public MExpressionTermArgument[] getArgumentsPlaces() { return args(DoubleMExpression.class, 1, 2); }
		public MExpression<?> from(List<MExpression<?>> args) { return new MiddleMExpression((DoubleMExpression)args.get(0),(DoubleMExpression)args.get(1)); }
		public String toString() {return "mid";}
	};
	
	// Unary Operator
	public static final MExpressionTerm FACTORIAL=new MExpressionTerm() {
		public int getPriority() { return 850; }
		public int getDirection() { return 1; }
		@Override public MExpressionTermArgument[] getArgumentsPlaces() { return args(DoubleMExpression.class, -1); }
		public MExpression<?> from(List<MExpression<?>> args) { return new FactorialMExpression((DoubleMExpression)args.get(0)); }
		public String toString() {return "!";}
	};
	
	public static final MExpressionTerm ABSOLUTE=new MExpressionTerm() {
		public int getPriority() { return 800; }
		public int getDirection() { return -1; }
		@Override public MExpressionTermArgument[] getArgumentsPlaces() { return args(DoubleMExpression.class, 1); }
		public MExpression<?> from(List<MExpression<?>> args) { return new AbsoluteMExpression((DoubleMExpression)args.get(0)); }
		public String toString() {return "absolute";}
	};
	
	public static final MExpressionTerm SQRT=new MExpressionTerm() {
		public int getPriority() { return 800; }
		public int getDirection() { return -1; }
		@Override public MExpressionTermArgument[] getArgumentsPlaces() { return args(DoubleMExpression.class, 1); }
		public MExpression<?> from(List<MExpression<?>> args) { return new SquareRootMExpression((DoubleMExpression)args.get(0)); }
		public String toString() {return "square root";}
	};
	
	// Comparator Operator
	public static final MExpressionTerm GREATER_THAN=new MExpressionTerm() {
		public int getPriority() { return 500; }
		public int getDirection() { return 1; }
		@Override public MExpressionTermArgument[] getArgumentsPlaces() { return args(DoubleMExpression.class, -1, 1); }
		public MExpression<?> from(List<MExpression<?>> args) { return new GreaterThanMExpression((DoubleMExpression)args.get(0),(DoubleMExpression)args.get(1)); }
		public String toString() {return "greater than";}
	};
	
	public static final MExpressionTerm LOWER_THAN=new MExpressionTerm() {
		public int getPriority() { return 500; }
		public int getDirection() { return 1; }
		@Override public MExpressionTermArgument[] getArgumentsPlaces() { return args(DoubleMExpression.class, -1, 1); }
		public MExpression<?> from(List<MExpression<?>> args) { return new LowerThanMExpression((DoubleMExpression)args.get(0),(DoubleMExpression)args.get(1)); }
		public String toString() {return "lower than";}
	};
	
	public static final MExpressionTerm EQUAL=new MExpressionTerm() {
		public int getPriority() { return 500; }
		public int getDirection() { return 1; }
		@Override public MExpressionTermArgument[] getArgumentsPlaces() { return args(DoubleMExpression.class, -1, 1); }
		public MExpression<?> from(List<MExpression<?>> args) { return new EqualMExpression((DoubleMExpression)args.get(0),(DoubleMExpression)args.get(1)); }
		public String toString() {return "equal";}
	};
	
	
	// Boolean Operator
	public static final MExpressionTerm AND=new MExpressionTerm() {
		public int getPriority() { return 300; }
		public int getDirection() { return 1; }
		@Override public MExpressionTermArgument[] getArgumentsPlaces() { return args(BooleanMExpression.class, -1, 1); }
		public MExpression<?> from(List<MExpression<?>> args) { return new AndMExpression((BooleanMExpression)args.get(0),(BooleanMExpression)args.get(1)); }
		public String toString() {return "and";}
	};
	
	public static final MExpressionTerm OR=new MExpressionTerm() {
		public int getPriority() { return 200; }
		public int getDirection() { return 1; }
		@Override public MExpressionTermArgument[] getArgumentsPlaces() { return args(BooleanMExpression.class, -1, 1); }
		public MExpression<?> from(List<MExpression<?>> args) { return new OrMExpression((BooleanMExpression)args.get(0),(BooleanMExpression)args.get(1)); }
		public String toString() {return "or";}
	};
	
	public static final MExpressionTerm NOT=new MExpressionTerm() {
		public int getPriority() { return 400; }
		public int getDirection() { return -1; }
		@Override public MExpressionTermArgument[] getArgumentsPlaces() { return args(BooleanMExpression.class, 1); }
		public MExpression<?> from(List<MExpression<?>> args) { return new NotMExpression((BooleanMExpression)args.get(0)); }
		public String toString() {return "not";}
	};
	
	
	// Logical Operator
	public static final MExpressionTerm CONDITIONAL=new MExpressionTerm() {
		public int getPriority() { return 150; }
		public int getDirection() { return -1; }
		@Override public MExpressionTermArgument[] getArgumentsPlaces() { return args(arg(1, BooleanMExpression.class, true),arg(2, MExpression.class, true),arg(3, MExpression.class, true)); }
		public MExpression<?> from(List<MExpression<?>> args) {
			return new ConditionnalMExpression<>((BooleanMExpression)args.get(0),args.get(1),args.get(2));
		}
		public String toString() {return "if";}
	};
	
	public static final MExpressionTerm LOOP=new MExpressionTerm() {
		public int getPriority() { return 100; }
		public int getDirection() { return -1; }
		@Override public MExpressionTermArgument[] getArgumentsPlaces() { return args(arg(1, BooleanMExpression.class, true),arg(2, MExpression.class, true),arg(3, MExpression.class, true)); }
		public MExpression<?> from(List<MExpression<?>> args) { return new LoopMExpression<>((BooleanMExpression)args.get(0),args.get(1),args.get(2)); }
		public String toString() {return "loop";}
	};
	
	public static final MExpressionTerm MULTIPLE=new MExpressionTerm() {
		public int getPriority() { return 50; }
		public int getDirection() { return -1; }
		@Override public MExpressionTermArgument[] getArgumentsPlaces() { return args(arg(1, MExpression.class, true),arg(2, MExpression.class, false),arg(3, MExpression.class, false),arg(4, MExpression.class, false),arg(5, MExpression.class, false),arg(6, MExpression.class, false),arg(7, MExpression.class, false)); }
		public MExpression<?> from(List<MExpression<?>> args) { return new MultipleMExpression<>(args.toArray(new MExpression<?>[args.size()])); }
		public String toString() {return "multiple";}
	};
	
	
	// String Operator
	public static final MExpressionTerm litteral(String v) {
		return new MExpressionTerm() {
			public int getPriority() { return 1000; }
			public int getDirection() { return 1; }
			@Override public MExpressionTermArgument[] getArgumentsPlaces() { return empty(); }
			public MExpression<?> from(List<MExpression<?>> args) { return new StringLitteralMExpression(v); }
			public String toString() {return "string \""+v+"\"";}
		};
	}
	
	public static final MExpressionTerm CONCAT=new MExpressionTerm() {
		public int getPriority() { return 160; }
		public int getDirection() { return 1; }
		@Override public MExpressionTermArgument[] getArgumentsPlaces() { return args(StringMExpression.class,-1,1); }
		public MExpression<?> from(List<MExpression<?>> args) { return new ConcatMExpression((StringMExpression)args.get(0),(StringMExpression)args.get(1)); }
		public String toString() {return "concat";}
	};
	
	public static final MExpressionTerm TOSTRING=new MExpressionTerm() {
		public int getPriority() { return 170; }
		public int getDirection() { return -1; }
		@Override public MExpressionTermArgument[] getArgumentsPlaces() { return args(DoubleMExpression.class, 1); }
		public MExpression<?> from(List<MExpression<?>> args) { return new ToStringMExpression((DoubleMExpression)args.get(0)); }
		public String toString() {return "tostring";}
	};
	
}
