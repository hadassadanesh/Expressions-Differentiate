/**
 * @author Hadassa Danesh
 * @ID 322567041
 * @version 1.0
 * @since 2020-05-14
 */

import java.util.List;
import java.util.Map;

/**
 * a class that indicates an expression.
 */
public interface Expression {
    /**
     * Evaluate the expression using the variable values provided
     * in the assignment, and return the result.  If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     *
     * @param assignment contains keys and their values.
     * @return the value of the expression.
     * @throws Exception when: 1. in Div- the second expression is 0.
     *                   2. in Log- when the base is one or less than one, and when the value of the
     *                   second expression is 0 or less than 0.
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * A convenience method. Like the `evaluate(assignment)` method above,
     * but uses an empty assignment.
     * <p>
     *
     * @return the value of the expression.
     * @throws Exception when: 1. in Div- the second expression is 0.
     *                   2. in Log- when the base is one or less than one, and when the value of the
     *                   second expression is 0 or less than 0.
     */
    double evaluate() throws Exception;

    /**
     * Returns a list of the variables in the expression.
     *
     * @return a list of the variables in the expression.
     */
    List<String> getVariables();

    /**
     * return a string representation of the expression.
     *
     * @return a string representation of the expression.
     */
    String toString();

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
    Expression assign(String var, Expression expression);

    /**
     * Returns the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     *
     * @param var the variable that the method differentiate the expression according to.
     * @return the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     */
    Expression differentiate(String var);

    /**
     * Returned a simplified version of the current expression.
     * <p>
     *
     * @return a simplified version of the current expression.
     */
    Expression simplify();
}