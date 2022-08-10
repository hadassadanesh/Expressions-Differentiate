/**
 * @author Hadassa Danesh
 * @ID 322567041
 * @version 1.0
 * @since 2020-05-14
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * a class that indicating a variable.
 */
public class Var implements Expression {
    private String variable;

    /**
     * a constructor that gets a string and initialize a new variable.
     * <p>
     *
     * @param var the given string
     */
    public Var(String var) {
        this.variable = var;
    }

    /**
     * if the current variable is in the assignment, then the method returns it.
     * If the variable is not in the assignment, an exception is thrown.
     * <p>
     *
     * @param assignment contains keys and their values.
     * @return the value of the variable, or un exception.
     * @throws Exception when a variable is not in the assignment.
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (!assignment.containsKey(getTheVariable())) {
            throw new Exception("variable is not in the assignment");
        }
        return assignment.get(getTheVariable());
    }

    /**
     * the assignment is empty, so the method can not return the value of the variable.
     * so, the method throws an exception.
     * <p>
     *
     * @return the value of the variable, at this case, throws exception.
     * @throws Exception when a variable is not in the assignment.
     */
    @Override
    public double evaluate() throws Exception {
        throw new Exception("variable is not in the assignment!");
    }

    /**
     * Returns a list with the current variable.
     * <p>
     *
     * @return a list with the current variable.
     */
    @Override
    public List<String> getVariables() {
        List<String> listOfVars = new ArrayList<>();
        listOfVars.add(getTheVariable());
        return listOfVars;
    }

    /**
     * Returns a string representation of the variable.
     * <p>
     *
     * @return the string representation of the variable.
     */
    public String toString() {
        return getTheVariable();
    }

    /**
     * Returns a new expression in which the variable is replaced with.
     * the provided expression (the method Does not modify the current expression).
     * <p>
     *
     * @param var        the variable that the method replaces.
     * @param expression the expression that the method will replace the variable to be.
     * @return a new expression in which the variable is replaced with the provided expression.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        if (this.toString().equals(var)) {
            return expression;
        }
        //if it is not the variable that the method needs to replace, return it.
        return this;
    }

    /**
     * Returns the expression tree resulting from differentiating.
     * the current variable relative to variable `var`.
     * <p>
     *
     * @param var the variable that the method differentiate the expression according to.
     * @return an expression: the number 1 if the current variable is the variable that the method
     * differentiate according to, and 0 otherwise.
     */
    public Expression differentiate(String var) {
        //if this value is equal to 'var'
        if (getTheVariable().equals(var)) {
            return new Num(1);
        }
        //else
        return new Num(0);
    }

    /**
     * returns the current variable.
     * <p>
     *
     * @return the current variable.
     */
    public String getTheVariable() {
        return this.variable;
    }

    /**
     * Returned a simplified version of the current expression, at this case, returns the current variable.
     * <p>
     *
     * @return a simplified version of the current expression.
     */
    public Expression simplify() {
        return this;
    }
}
