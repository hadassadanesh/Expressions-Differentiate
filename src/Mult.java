/**
 * @author Hadassa Danesh
 * @ID 322567041
 * @version 1.0
 * @since 2020-05-14
 */

import java.util.Map;

/**
 * a class that indicating the multiplication of one expression with another expression.
 */
public class Mult extends BinaryExpression implements Expression {

    /**
     * a constructor that gets two expressions and initialize a Mult object.
     * the initialization is done in the super class- BinaryExpression.
     * <p>
     *
     * @param firstExpression  the first expression of the Mult object.
     * @param secondExpression the second expression of the Mult object.
     */
    public Mult(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression);
    }


    /**
     * Evaluate the expression using the variable values provided
     * in the assignment, and return the result.
     * <p>
     *
     * @param assignment contains keys and their values.
     * @return the value of the expression.
     * @throws Exception exception
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {

        /*evaluate the first expression with the given assignment, and multiply it by the
         value of the second expression with the given assignment.*/
        return super.getFirstExpression().evaluate(assignment) * super.getSecondExpression().evaluate(assignment);
    }

    /**
     * Evaluate the expression and return the result.
     * <p>
     *
     * @return the value of the expression.
     * @throws Exception exception
     */
    @Override
    public double evaluate() throws Exception {
        //evaluate the first expression  and multiply it by the value of the second expression.
        return super.getFirstExpression().evaluate() * super.getSecondExpression().evaluate();
    }

    /**
     * Returns a string representation of the Mult object.
     * <p>
     *
     * @return a string representation of the Mult object.
     */
    public String toString() {
        return "(" + getFirstExpression().toString() + " * " + getSecondExpression().toString() + ")";
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

        /*create a new Mult object that the expressions in it are the two
        expressions after doing assign on them.*/
        return new Mult(getFirstExpression().assign(var, expression),
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
        // the method uses the mathematic formula: (f*g)'= f'*g + f*g'
        //the differentiate of the first expression
        Expression differentiateOfFirst = getFirstExpression().differentiate(var);
        //the differentiate of the second expression
        Expression differentiateOfSecond = getSecondExpression().differentiate(var);
        // finds f'*g in the formula
        Expression firstMult = new Mult(differentiateOfFirst, getSecondExpression());
        // finds f*g' in the formula
        Expression secondMult = new Mult(getFirstExpression(), differentiateOfSecond);
        // return the addition of the two Multiplications.
        return new Plus(firstMult, secondMult);
    }

    /**
     * Returns a simplified version of the current expression.
     * <p>
     *
     * @return a simplified version of the current expression.
     */
    public Expression simplify() {
        //initialize a Num to be zero.
        Num zero = new Num(0);
        //initialize a Num to be one.
        Num one = new Num(1);
        //create a new Expression that saves the simplification of the first expression.
        Expression theFirstExpression = getFirstExpression().simplify();
        //create a new Expression that saves the simplification of the second expression.
        Expression theSecondExpression = getSecondExpression().simplify();

        /* if there are no variables in the current expression, use a method that is in the
         BaseExpression, that handle this case.*/
        if (getVariables().isEmpty()) {
            return handleExceptionInSimplify();
        } else if (theFirstExpression.toString().equals(zero.toString())
                || (theSecondExpression.toString().equals(zero.toString()))) {
            //if one of the expressions that are in the Mult expression is 0, return 0.
            return zero;
        } else if (theFirstExpression.toString().equals(one.toString())) {
            // if the first expression in the Mult expression is 1, return the second expression.
            return theSecondExpression;
        } else if (theSecondExpression.toString().equals(one.toString())) {
            // if the second expression in the Mult expression is 1, return the first expression.
            return theFirstExpression;
        }
        //return a new Mult expression with the two simplified expressions in it.
        return new Mult(theFirstExpression.simplify(), theSecondExpression.simplify());
    }
}