# Math Expression Interpreter
Fast and modular math Java math expression interpreter.
Exemple of use:
```java
// Convert token list into a list of MExpressionTerm
// A list of MExpressionTerm describe an math expression but is not directly useable.
String str[]= {"19","+","26","*","45","/","X"};
MExpressionSerializer serializer=MExpressionSerializer.createBaseMExpressionSerializer();
List<MExpressionTerm> terms=serializer.serialize(Arrays.stream(str).iterator());

// Convert a list of MExpressionTerm into an MExpression Object you can use.
MExpressionBuilder builder=new MExpressionBuilder();
MExpression expression=builder.compile(terms);

// You can get the list of the expression parameters
List<String> parameters=expression.getParameters();

// You can get a result
Map<String,Double> parameters=new HashMap<>();
parameters.put("X", 3.4d);
double result=expression.get(parameters);
```
