public class StringToNode {
    String string = "(3,(2,4)+)*";

    public StringToNode(String string) {
        this.string = string;
    }

    public Operator calculate() {
        String operator = String.valueOf(string.charAt(string.length()-1));
        String[] arr;
        double number1;
        double number2;
        string = string.replace("(", "");
        string = string.replace(")", "");
        arr = string.split(",");
        number1 = Double.parseDouble(arr[0]);
        number2 = Double.parseDouble(arr[1].replace(operator, ""));

        switch (operator) {
            case "+" -> {
                return new Operator(number1, number2, Operators.ADD);
            }
            case "-" -> {
                return new Operator(number1, number2, Operators.SUBTRACT);
            }
            case "*" -> {
                return new Operator(number1, number2, Operators.MULTIPLY);
            }
            case "/" -> {
                return new Operator(number1, number2, Operators.DIVIDE);
            }
            case "^" -> {
                return new Operator(number1, number2, Operators.POWER);
            }
            default -> {
                return null;
            }
        }
    }

}
