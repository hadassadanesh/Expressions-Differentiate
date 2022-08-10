/**
 * @author Hadassa Danesh
 * @ID 322567041
 * @version 1.0
 * @since 2020-05-14
 */

import java.util.Map;

/**
 * a class that indicating the cosinus of the value of an expression.
 */
public class Cos extends UnaryExpression implements Expression {

    /**
     * a constructor that gets an expression and initialize a Cos object.
     * the initialization is done in the super class- UnaryExpression.
     * <p>
     *
     * @param theExpression an expression
     */

    public Cos(Expression theExpression) {
        super(theExpression);
    }

    /**
     * Evaluate the expression using the variable values provided.
     * in the assignment, and return the result, after doing cos on it.
     * <p>
     *
     * @param assignment contains keys and their values.
     * @return the value of the expression after transposing to radians and doing cos on the answer.
     * @throws Exception exception
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {

        /* evaluate the current expression with the given assignment, and after that, change the answer
        into radians and do cos on the answer. return the result. */
        return Math.cos(Math.toRadians(getTheExpression().evaluate(assignment)));
    }

    /**
     * Evaluate the expression, and returns the result after doing cos on it.
     * <p>
     *
     * @return the value of the expression after transposing to radians and doing cos on the answer.
     * @throws Exception exception
     */
    @Override
    public double evaluate() throws Exception {

         /* evaluate the current expression, and after that, change the answer
        into radians and do cos on the answer. return the result. */
        return Math.cos(Math.toRadians(getTheExpression().evaluate()));
    }

    /**
     * Returns a string representation of the Cos object.
     * <p>
     *
     * @return the string representation of the Cos object.
     */
    public String toString() {
        return "cos(" + getTheExpression() + ")";
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
        //create a new Cos object that the expression in it is the current expression after doing assign on it.
        return new Cos(getTheExpression().assign(var, expression));
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
        // the differentiate of the current expression (without cos).
        Expression differentiateOfExpression = getTheExpression().differentiate(var);
        // return the differentiate of the expression with Cos on it.
        return new Neg(new Mult(differentiateOfExpression, new Sin(getTheExpression())));
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
        //return a new cos expression with a simplified expression in it.
        return new Cos(theFirstExpression.simplify());
    }
}
