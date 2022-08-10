/**
 * @author Hadassa Danesh
 * ID 322567041
 * @version 1.0
 * @since 2020-05-14
 */

/**
 * a test.
 */
public class ExpressionsTest {
    /**
     * a main method that:.
     * Create the expression (2x) + (sin(4y)) + (e^x).
     * Print the expression.
     * Print the value of the expression with (x=2,y=0.25,e=2.71).
     * Print the differentiated expression according to x.
     * Print the value of the differentiated expression according to x with the assignment above.
     * Print the simplified differentiated expression.
     * <p>
     *
     * @param args an array of strings.
     */
    public static void main(String[] args) {
        //Create the expression (2x) + (sin(4y)) + (e^x).
        Expression expression = new Plus(new Mult(new Num(2), new Var("x")), new Plus(new Sin(
                new Mult(new Num(4), new Var("y"))), new Pow(new Var("e"), new Var("x"))));
        //Print the expression.
        System.out.println(expression.toString());
        // create a Map object.
        java.util.Map<String, Double> assignment = new java.util.TreeMap<String, Double>();
        // add to the assignment the keys and values: x=2,y=0.25,e=2.71.
        assignment.put("x", 2.0);
        assignment.put("y", 0.25);
        assignment.put("e", 2.71);
        //Print the value of the expression with (x=2,y=0.25,e=2.71).
        try {
            System.out.println(expression.evaluate(assignment));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        //Print the differentiated expression according to x.
        System.out.println(expression.differentiate("x"));
        //Print the value of the differentiated expression according to x with the assignment above
        try {
            System.out.println(expression.differentiate("x").evaluate(assignment));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        //Print the simplified differentiated expression.
        System.out.println(expression.differentiate("x").simplify());
    }
}