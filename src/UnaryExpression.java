/**
 * @author Hadassa Danesh
 * @ID 322567041
 * @version 1.0
 * @since 2020-05-14
 */

import java.util.List;
import java.util.Map;

/**
 * a class that indicating an expression that includes one expression in it.
 */
public abstract class UnaryExpression extends BaseExpression {
    //every unary expression includes one expression.
    private Expression firstExpression;

    /**
     * a constructor that gets an expression, and initializes the field of the class.
     * the constructor is protected, because we want to use it just in this class or in the sub classes.
     * <p>
     *
     * @param theExpression the expression that the class gets.
     */
    protected UnaryExpression(Expression theExpression) {
        // calls the empty constructor of the super class- BaseExpression.
        super();
        // initializes the field of the class.
        this.firstExpression = theExpression;
    }

    /**
     * Evaluate the expression using the variable values provided
     * in the assignment, and return the result.  If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     *
     * @param assignment contains keys and their values.
     * @return the value of the expression.
     * @throws Exception exception
     */
    public abstract double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * A convenience method. Like the `evaluate(assignment)` method above,
     * but uses an empty assignment.
     * <p>
     *
     * @return the value of the expression.
     * @throws Exception exception
     */
    public abstract double evaluate() throws Exception;

    /**
     * Returns a list of the variables in the expression.
     *
     * @return a list of the variables in the expression.
     */
    public List<String> getVariables() {
        // return a list with the variables of the expression. this method uses a recursion.
        return getTheExpression().getVariables();
    }

    /**
     * return a string representation of the expression.
     *
     * @return a string representation of the expression.
     */
    public abstract String toString();

    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     *
     * @param var        the variable that the method replaces.
     * @param expression the expression that the method will replace the variable to be.
     * @return a new expression in which all occurrences of the variable
     * var are replaced with the provided expression
     */
    public abstract Expression assign(String var, Expression expression);

    /**
     * Returns the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     *
     * @param var the variable that the method differentiate the expression according to.
     * @return the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     */
    public abstract Expression differentiate(String var);

    /**
     * Returned a simplified version of the current expression.
     * <p>
     *
     * @return a simplified version of the current expression.
     */
    public abstract Expression simplify();

    /**
     * return the expression in this unary expression.
     *
     * @return the expression in this unary expression.
     */
    protected Expression getTheExpression() {
        return firstExpression;
    }

}
