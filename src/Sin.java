/**
 * @author Hadassa Danesh
 * @ID 322567041
 * @version 1.0
 * @since 2020-05-14
 */

import java.util.Map;

/**
 * a class that indicating the sinus of the value of an expression.
 */
public class Sin extends UnaryExpression implements Expression {

    /**
     * a constructor that gets an expression and initialize a Sin object.
     * the initialization is done in the super class- UnaryExpression.
     * <p>
     *
     * @param theExpression an expression
     */
    public Sin(Expression theExpression) {
        super(theExpression);
    }

    /**
     * Evaluate the expression using the variable values provided.
     * in the assignment, and return the result, after doing sin on it.
     * <p>
     *
     * @param assignment contains keys and their values.
     * @return the value of the expression after transposing to radians and doing sin on the answer.
     * @throws Exception exception
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {

        /* evaluate the current expression with the given assignment, and after that, change the answer
        into radians and do sin on the answer. return the result. */
        return Math.sin(Math.toRadians(getTheExpression().evaluate(assignment)));
    }

    /**
     * Evaluate the expression, and returns the result after doing sin on it.
     * <p>
     *
     * @return the value of the expression after transposing to radians and doing sin on the answer.
     * @throws Exception exception
     */
    @Override
    public double evaluate() throws Exception {

         /* evaluate the current expression, and after that, change the answer
        into radians and do sin on the answer. return the result. */
        return Math.sin(Math.toRadians(getTheExpression().evaluate()));
    }

    /**
     * Returns a string representation of the Sin object.
     * <p>
     *
     * @return the string representation of the Sin object.
     */
    public String toString() {
        return "sin(" + getTheExpression() + ")";
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
        //create a new Sin object that the expression in it is the current expression after doing assign on it.
        return new Sin(getTheExpression().assign(var, expression));
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
        // the differentiate of the current expression (without sin).
        Expression differentiateOfFirst = getTheExpression().differentiate(var);
        // return the differentiate of the expression with Sin on it.
        return new Mult(differentiateOfFirst, new Cos(getTheExpression()));
    }

    /**
     * Returned a simplified version of the current expression.
     * <p>
     *
     * @return a simplified version of the current expression.
     */
    public Expression simplify() {
        //create a new Expression that saves the simplification of the current expression.
        Expression theFirstExpression = getTheExpression().simplify();

        /* if there are no variables in the current expression, use a method that is in the
         BaseExpression, that handle this case.*/
        if (getVariables().isEmpty()) {
            return handleExceptionInSimplify();
        }
        //return a new sin expression with a simplified expression in it.
        return new Sin(theFirstExpression.simplify());
    }
}
