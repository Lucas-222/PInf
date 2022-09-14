public class Test {

    public static void main(String[] args) throws Exception {
        // First Operator
        Value firstLeftValue = new Value(10);
        Value firstRightValue = new Value(5);
        Operator operator1 = new Operator(firstLeftValue, firstRightValue, Operators.MULTIPLY);

        // Second Operator
        Value secondRightValue = new Value(15);
        Operator operator2 = new Operator(operator1, secondRightValue, Operators.ADD);

        // Output
        System.out.println(operator2.getValue());

    }

}
