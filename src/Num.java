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
 * a class that indicating a number.
 */
public class Num implements Expression {
    private double num;

    /**
     * a constructor that gets a number from the type double, and initialize a new number.
     * <p>
     *
     * @param num the number.
     */
    public Num(double num) {
        this.num = num;
    }

    /**
     * Evaluate the number and return the result.
     * <p>
     *
     * @param assignment contains keys and their values.
     * @return the current number.
     * @throws Exception exception
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return getNum();
    }

    /**
     * Evaluate the number and return the result.
     * <p>
     *
     * @return the current number.
     * @throws Exception exception
     */
    @Override
    public double evaluate() throws Exception {
        return getNum();
    }

    /**
     * Returns a list of the variables in the expression- at this case, an empty list.
     * <p>
     *
     * @return an empty list.
     */
    @Override
    public List<String> getVariables() {
        return new ArrayList<>();
    }

    /**
     * Returns a string representation of the number.
     * <p>
     *
     * @return a string representation of the number.
     */
    public String toString() {
        return Double.toString(getNum());
    }

    /**
     * Returns a new expression in which the variable is replaced with.
     * the provided expression. at this case, this is a number and not a variable,
     * so the method returns the current number.
     * <p>
     *
     * @param var        the variable that the method replaces.
     * @param expression the expression that the method will replace the variable to be.
     * @return the current number.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return this;
    }

    /**
     * return the current number.
     * <p>
     *
     * @return the current number, in double type.
     */
    public double getNum() {
        return this.num;
    }

    /**
     * Returns the expression tree resulting from differentiating.
     * the current variable relative to variable `var`.
     * <p>
     * @param var the variable that the method differentiate the expression according to.
     * @return an expression: the number 1 if the current variable is the variable that the method
     * differentiate according to, and 0 otherwise. */

    /**
     * returns the differentiate of the current number, 0.
     * <p>
     *
     * @param var the variable that the method differentiate the expression according to.
     * @return the differentiate of the current number, 0.
     */
    public Expression differentiate(String var) {
        return new Num(0);
    }

    /**
     * Returned a simplified version of the current expression, at this case, returns the current number.
     * <p>
     *
     * @return a simplified version of the current expression.
     */
    public Expression simplify() {
        return this;
    }
}
