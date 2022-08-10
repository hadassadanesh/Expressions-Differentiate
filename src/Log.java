/**
 * @author Hadassa Danesh
 * @ID 322567041
 * @version 1.0
 * @since 2020-05-14
 */

import java.util.Map;

/**
 * a class that indicating the log of one expression when the base is another expression.
 */
public class Log extends BinaryExpression implements Expression {

    /**
     * a constructor that gets two expressions and initialize a Log object.
     * the initialization is done in the super class- BinaryExpression.
     * <p>
     *
     * @param firstExpression  the base of the log.
     * @param secondExpression the expression that is in the log.
     */
    public Log(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression);
    }

    /**
     * Evaluate the expression using the variable values provided.
     * in the assignment, and return the result. the method throws an exception when
     * the base of the log is the number 1 or a number less than 1. the method throw exception
     * also if the value of the function (the second expression of the expression) is 0 or less than 0.
     * <p>
     *
     * @param assignment contains keys and their values.
     * @return the value of the expression.
     * @throws Exception when the base is one or less than one, and when the value of the
     *                   second expression is 0 or less than 0
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        // if the base of the log is 1 or less than 1, the method throws an exception.
        if (getFirstExpression().evaluate(assignment) <= 1) {
            throw new Exception("the base is one or less than one");
        } else if (getSecondExpression().evaluate(assignment) <= 0) {

            /*if the value of the function (the second expression of the expression) is 0 or less
             than 0, the method throws an exception. */
            throw new Exception("the value of the function is 0 or less than 0");
        }

        /* use a mathematic formula: log(base, func)= log(10, func)/ log(10, base). the method uses
        the log method in the Math class to envalue the current expression.*/
        return (Math.log(super.getSecondExpression().evaluate(assignment))
                / Math.log(super.getFirstExpression().evaluate(assignment)));
    }


    /**
     * Evaluate the expression and return the result.the method throws an exception when
     * the base of the log is the number 1 or a number less than 1. the method throw exception
     * also if the value of the function (the second expression of the expression) is 0 or less than 0.
     * <p>
     *
     * @return the value of the expression.
     * @throws Exception when the base is one or less than one, and when the value of the
     *                   second expression is 0 or less than 0
     */
    @Override
    public double evaluate() throws Exception {
        // if the base of the log is 1 or less than 1, the method throws an exception.
        if (getFirstExpression().evaluate() <= 1) {
            throw new Exception("the base is one or less than one");
        } else if (getSecondExpression().evaluate() <= 0) {

            /*if the value of the function (the second expression of the expression) is 0 or less
             than 0, the method throws an exception. */
            throw new Exception("the value of the function is 0 or less than 0");
        }

        /* use a mathematic formula: log(base, func)= log(10, func)/ log(10, base). the method uses
        the log method in the Math class to envalue the current expression.*/
        return (Math.log(super.getSecondExpression().evaluate()) / Math.log(super.getFirstExpression().evaluate()));
    }

    /**
     * Returns a string representation of the Log object.
     * <p>
     *
     * @return a string representation of the Log object.
     */
    public String toString() {
        return "log(" + this.getFirstExpression() + ", " + this.getSecondExpression() + ")";
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

        /*create a new Log object that the expression in it is: the first expression after assignment
         * and the second expression after assignment.*/
        return new Log(getFirstExpression().assign(var, expression),
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

        /* the method uses the formula: If the function is log(f(x)) so the differentiate
         is f'(x) / (ln (base) * f(x)).*/
        // the first expression is the base of the log
        Expression base = getFirstExpression();
        // the second expression is the function of the log
        Expression functionF = getSecondExpression();
        // save the differentiate of the second expression
        Expression differentiateOfF = functionF.differentiate(var);
        // finds ln (base).
        Expression lnOfTheBase = new Log(new Var("e"), base);
        //creates an expression of the denominator of the differentiate.
        Expression theDenominator = new Mult(lnOfTheBase, functionF);
        // return the differentiate of the Log object.
        return new Div(differentiateOfF, theDenominator);
    }

    /**
     * Returns a simplified version of the current expression.
     * <p>
     *
     * @return a simplified version of the current expression.
     */
    public Expression simplify() {
        //create a new Expression that saves the simplification of the first expression.
        Expression theFirstExpression = getFirstExpression().simplify();
        //create a new Expression that saves the simplification of the second expression.
        Expression theSecondExpression = getSecondExpression().simplify();

        /* if there are no variables in the current expression, use a method that is in the
         BaseExpression, that handle this case.*/
        if (getVariables().isEmpty()) {
            return handleExceptionInSimplify();
        } else if (theFirstExpression.toString().equals(theSecondExpression.toString())) {
            //if the first expression (base) and the second expression are the same, return 1.
            return new Num(1);
        }
        //return a new Log expression with the two simplified expressions in it.
        return new Log(theFirstExpression.simplify(), theSecondExpression.simplify());
    }
}
