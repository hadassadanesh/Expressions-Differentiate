/**
 * @author Hadassa Danesh
 * @ID 322567041
 * @version 1.0
 * @since 2020-05-14
 */

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * a class that indicating an expression that includes two expressions in it.
 */
public abstract class BinaryExpression extends BaseExpression {
    //every binary expression includes two expressions.
    private Expression firstExpression;
    private Expression secondExpression;

    /**
     * a constructor that gets two expressions, and initializes the fields of the class.
     * the constructor is protected, because we want to use it just in this class or in the sub classes.
     * <p>
     *
     * @param firstExpression  the first expression that the class gets.
     * @param secondExpression the second expression that the class gets.
     */
    protected BinaryExpression(Expression firstExpression, Expression secondExpression) {
        // calls the empty constructor of the super class- BaseExpression.
        super();
        // initializes the fields of the class.
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }

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
    public abstract double evaluate(Map<String, Double> assignment) throws Exception;

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
    public abstract double evaluate() throws Exception;


    /**
     * Returns a list of the variables in the expression.
     *
     * @return a list of the variables in the expression.
     */
    public List<String> getVariables() {
        // saves in a list the variables of the first expression of the binary expression.
        List<String> listOfVarsFirstExpression = getFirstExpression().getVariables();
        // saves in a list the variables of the second expression of the binary expression.
        List<String> listOfVarsInSecondExpression = getSecondExpression().getVariables();
        // goes over the list of the vars in the second expression, and add every var of it to the first list.
        for (String var : listOfVarsInSecondExpression) {
            listOfVarsFirstExpression.add(var);
        }
        // return the list with all the variables, where every variable appears just once.
        return listOfVarsFirstExpression.stream().distinct().collect(Collectors.toList());
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
     * return the first expression of the binary expression.
     * <p>
     *
     * @return the first expression of the binary expression.
     */
    protected Expression getFirstExpression() {
        return firstExpression;
    }


    /**
     * return the second expression of the binary expression.
     * <p>
     *
     * @return the second expression of the binary expression.
     */
    protected Expression getSecondExpression() {
        return secondExpression;
    }

}




