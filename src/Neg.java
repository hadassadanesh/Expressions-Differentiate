/**
 * @author Hadassa Danesh
 * @ID 322567041
 * @version 1.0
 * @since 2020-05-14
 */

import java.util.Map;

/**
 * a class that indicating the multiplying of one expression by -1.
 */
public class Neg extends UnaryExpression implements Expression {

    /**
     * a constructor that gets an expression and initialize a Neg object.
     * the initialization is done in the super class- UnaryExpression.
     * <p>
     *
     * @param theExpression an expression
     */
    public Neg(Expression theExpression) {
        super(theExpression);
    }

    /**
     * Evaluate the expression using the variable values provided.
     * in the assignment, and return the result, after multiplying it by -1.
     * <p>
     *
     * @param assignment contains keys and their values.
     * @return the value of the expression after multiply it by -1.
     * @throws Exception exception
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        //evaluate the current expression with the given assignment, and after that, multiply it by -1.
        return -1 * getTheExpression().evaluate(assignment);
    }

    /**
     * Evaluate the expression and return the result, after multiplying it by -1.
     * <p>
     *
     * @return the value of the expression after multiply it by -1.
     * @throws Exception exception
     */
    @Override
    public double evaluate() throws Exception {
        //evaluate the current expression, and after that, multiply it by -1.
        return -1 * getTheExpression().evaluate();
    }

    /**
     * Returns a string representation of the Neg object.
     * <p>
     *
     * @return the string representation of the Neg object.
     */
    public String toString() {

        /* if the current expression starts with the char - , then, replace the - with nothing.
         * this is necessary for- if the expression is a negative number, the method returns it
         * without minuses. */
        if (getTheExpression().toString().startsWith("-")) {
            return getTheExpression().toString().replaceFirst("-", "");
        }
        // else, return the expression with Parenthesis and a minus.
        return "(-" + getTheExpression() + ")";
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
        //create a new Neg object that the expression in it is the current expression after doing assign on it.
        return new Neg(getTheExpression().assign(var, expression));
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
        return new Neg(getTheExpression().differentiate(var));
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
        //return a new Neg expression with a simplified expression in it.
        return new Neg(theFirstExpression.simplify());
    }
}
