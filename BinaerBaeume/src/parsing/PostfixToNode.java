package parsing;

import termAsTreeInheritance.*;
import java.util.List;

public class PostfixToNode {
    List<String> nodesAsString;

    public PostfixToNode(List<String> nodesAsString) {
        this.nodesAsString = nodesAsString;
    }

    public Node calcNode() {
        // Loop through every string in nodesAsString
        for (int i = 0; i < nodesAsString.size(); i++) {
            System.out.println(nodesAsString);
            // If there are two numbers before an operator | (1, 2, *)
            if (i+2 <= nodesAsString.size() && CharacterLists.isNumber(nodesAsString.get(i)) && CharacterLists.isNumber(nodesAsString.get(i+1)) && nodesAsString.get(i+2).length() == 1 && CharacterLists.OPERATORS.contains(nodesAsString.get(i+2).charAt(0))) {
                // Create nodes and remove the second number and the operator
                Node leftvalue = new Value(Double.parseDouble(nodesAsString.get(i)));
                Node rightvalue = new Value(Double.parseDouble(nodesAsString.remove(i+1)));
                Operator operator = getOperator(nodesAsString.remove(i+1), leftvalue, rightvalue);
                // Replace the first number with the solution | (1, 2, *) --> (2)
                nodesAsString.set(i, String.valueOf(operator.getValue()));
                // Start over with the array
                i = -1;
            }
        }
        return new Value(Double.parseDouble(nodesAsString.get(0)));
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
