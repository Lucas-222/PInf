public class StringToNode {
    String string;

    public StringToNode(String string) {
        this.string = string;
    }

    public Operator calculate() {
        String operator = String.valueOf(string.charAt(string.length()-1));
        string = string.replace("(", "").replace(")", "").replace(operator, "");
        String[] arr = string.split(",");

        double number1 = Double.parseDouble(arr[0]);
        double number2 = Double.parseDouble(arr[1]);

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
