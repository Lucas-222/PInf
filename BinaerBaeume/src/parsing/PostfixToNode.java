package parsing;

import termAsTreeInheritance.*;

import java.util.List;

public class PostfixToNode {
    List<String> nodesAsString;

    public PostfixToNode(List<String> nodesAsString) {
        this.nodesAsString = nodesAsString;
    }

    public Node calcNode() {
        // Chek if there is a part in the equation wich needs to get calculated first
        calculateEquationsWithLeftAssociativity();
        // If the size of nodesAsString is one, no calculation can be performed | example: (4)
        if (nodesAsString.size() == 1) return new Value(Double.parseDouble(nodesAsString.get(0)));
        // Initialize nodes with null
        Operator operator = null;
        Node leftvalue = null;
        Node rightvalue = null;

        // Loop through every String in reversed polish notation
        for (String s : nodesAsString) {
            // If string is a number
            if (CharacterLists.isNumber(s)) {
                // If leftvalue is null, initialize it | After it was initialized it gets updated automaticly with the new operator
                if (leftvalue == null) {
                    leftvalue = new Value(Double.parseDouble(s));
                } else if (rightvalue == null) {
                    // After every calculation rightvalue gets set to null, and then filled with the new value
                    rightvalue = new Value(Double.parseDouble(s));
                }
            }
            // If string is an operator and the length is one, else a minus before a number would also count
            if (s.length() == 1 && CharacterLists.OPERATORS.contains(s.charAt(0))) {
                operator = getOperator(s, leftvalue, rightvalue);
                // The new left value is the old operator
                leftvalue = operator;
                // Rightvalue gets set to null, and filled with the next number, in the next pass through
                rightvalue = null;
            }
        }

        assert operator != null;
        return operator;
    }

    public void calculateEquationsWithLeftAssociativity() {
        // Chek if there is a part in the equation wich needs to get calculated first
        for (int i = 0; i < nodesAsString.size(); i++) {
            // If there are two numbers before an operator, wich stands for a multiplication or a bracket (1, 2, *)
            if (CharacterLists.isNumber(nodesAsString.get(i)) && CharacterLists.isNumber(nodesAsString.get(i+1)) && nodesAsString.get(i+2).length() == 1 && CharacterLists.OPERATORS.contains(nodesAsString.get(i+2).charAt(0))) {
                // Create nodes and remove the second number and the operator
                Node leftvalue = new Value(Double.parseDouble(nodesAsString.get(i)));
                Node rightvalue = new Value(Double.parseDouble(nodesAsString.remove(i+1)));
                Operator operator = getOperator(nodesAsString.remove(i+1), leftvalue, rightvalue);
                // Replace the first number with the solution | (1, 2, *) --> (2)
                nodesAsString.set(i, String.valueOf(operator.getValue()));
                // Start over with the array
                i = 0;
            }
        }
    }

    public Operator getOperator(String s, Node leftvalue, Node rightvalue) {
        // Return the right operator from string, with the values from leftvalue and rightvalue
        return switch (s) {
            case "+" -> new Add(leftvalue, rightvalue);
            case "-" -> new Subtract(leftvalue, rightvalue);
            case "*" -> new Multiply(leftvalue, rightvalue);
            case "/" -> new Divide(leftvalue, rightvalue);
            case "^" -> new Power(leftvalue, rightvalue);
            default -> null;
        };
    }

}
