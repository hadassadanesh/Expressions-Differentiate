/**
 * @author Hadassa Danesh
 * @ID 322567041
 * @version 1.0
 * @since 2020-05-14
 */

import java.util.Map;

/**
 * a class that indicating the division of two expressions.
 */
public class Div extends BinaryExpression implements Expression {

    /**
     * a constructor that gets two expressions and initialize a Div object.
     * the initialization is done in the super class- BinaryExpression.
     * <p>
     *
     * @param firstExpression  the first expression of the Div object, the divided.
     * @param secondExpression the second expression of the Plus object, the divisive.
     */
    public Div(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression);
    }


    /**
     * Evaluate the expression using the variable values provided.
     * in the assignment, and return the result. the method throws an exception when
     * the divided expression is the number 0.
     * <p>
     *
     * @param assignment contains keys and their values.
     * @return the value of the expression.
     * @throws Exception when the second expression is 0.
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {

        /*if the second expression is 0, it means that there is a division by 0,
         so the method throws an exception.*/
        if (getSecondExpression().evaluate(assignment) == 0) {
            throw new Exception("error dividing by zero");
        }

        /*evaluate the first expression with the given assignment, and divide it by the
         value of the second expression with the given assignment.*/
        return super.getFirstExpression().evaluate(assignment) / super.getSecondExpression().evaluate(assignment);
    }

    /**
     * Evaluate the expression and return the result. the method throws an exception when
     * the divided expression is the number 0.
     * <p>
     *
     * @return the value of the expression.
     * @throws Exception when the second expression is 0.
     */
    @Override
    public double evaluate() throws Exception {

        /*if the second expression is 0, it means that there is a division by 0,
         so the method throws an exception.*/
        if (getSecondExpression().evaluate() == 0) {
            throw new Exception("error dividing by zero");
        }
        //evaluate the first expression, and divide it by the value of the second expression.
        return super.getFirstExpression().evaluate() / super.getSecondExpression().evaluate();
    }

    /**
     * Returns a string representation of the Div object.
     * <p>
     *
     * @return a string representation of the Div object.
     */
    public String toString() {
        return "(" + getFirstExpression().toString() + " / " + getSecondExpression().toString() + ")";
    }

    /**
     * Returns a new expression in which all occurrences of the variable.
     * var are replaced with the provided expression (the method Does not modify the
     * current expression).
     * <p>
     *
     * @param var        the variable that the method replaces.
     * @param expression the expression that the method will replace the variable to be.
     * @return a new expression in which all occurrences of the variable
     * var are replaced with the provided expression
     */
    @Override
    public Expression assign(String var, Expression expression) {

        /*create a new Div object that the expression in it is: the first expression after assignment
         * and the second expression after assignment.*/
        return new Div(getFirstExpression().assign(var, expression),
                getSecondExpression().assign(var, expression));
    }

    /**
     * Returns the expression that is a result of differentiating the current.
     * expression relative to variable `var`.
     * <p>
     *
     * @param var the variable that the method differentiate the expression according to.
     * @return the expression that is a result of differentiating the current.
     * expression relative to variable `var`.
     */
    public Expression differentiate(String var) {
        //the method uses the formula: (f/g)'= ((f'g)-(g'f))/g^2.
        //save the differentiate of the first expression
        Expression differentiateOfFirst = getFirstExpression().differentiate(var);
        //save the differentiate of the second expression
        Expression differentiateOfSecond = getSecondExpression().differentiate(var);
        // create the first multiplication of the numerator.
        Expression firstMult = new Mult(differentiateOfFirst, getSecondExpression());
        // create the second multiplication of the numerator.
        Expression secondMult = new Mult(differentiateOfSecond, getFirstExpression());
        // create the numerator.
        Expression theNumerator = new Minus(firstMult, secondMult);
        // create the denominator.
        Expression theDenominator = new Pow(getSecondExpression(), new Num(2));
        // return the division of the numerator and the denominator.
        return new Div(theNumerator, theDenominator);
    }

    /**
     * Returns a simplified version of the current expression.
     * <p>
     *
     * @return a simplified version of the current expression.
     */
    public Expression simplify() {
        //initialize a Num to be 1
        Num one = new Num(1);
        //create a new Expression that saves the simplification of the first expression.
        Expression theFirstExpression = getFirstExpression().simplify();
        //create a new Expression that saves the simplification of the second expression.
        Expression theSecondExpression = getSecondExpression().simplify();

         /* if there are no variables in the current expression, use a method that is in the
         BaseExpression, that handle this case.*/
        if (getVariables().isEmpty()) {
            return handleExceptionInSimplify();
        } else if (theFirstExpression.toString().equals(theSecondExpression.toString())) {
            //if the first expression and the second expression are the same, return 1.
            return one;
        } else if (theSecondExpression.toString().equals(one.toString())) {
            //if the divide is 1, then return the first expression.
            return theFirstExpression;
        }
        //return a new Div expression with the two simplified expressions in it.
        return new Div(theFirstExpression.simplify(), theSecondExpression.simplify());
    }
}