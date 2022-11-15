package parsing;

import termAsTreeInheritance.*;
import java.util.List;

public class PostfixToNode {
    private final List<String> nodesAsStringList;
    private String variableReplacement;

    public PostfixToNode(List<String> nodesAsStringList) {
        this.nodesAsStringList = nodesAsStringList;
    }

    public PostfixToNode(List<String> nodesAsStringList, String variableReplacement) {
        this.nodesAsStringList = nodesAsStringList;
        this.variableReplacement = variableReplacement;
    }

    public Node calcNode() {
        replaceVariable();
        // Loop through every string in nodesAsString
        for (int i = 0; i < nodesAsStringList.size(); i++) {
            // If there are two numbers before an operator | (1, 2, *)
            if (i+2 <= nodesAsStringList.size() && CharacterLists.isNumber(nodesAsStringList.get(i)) && CharacterLists.isNumber(nodesAsStringList.get(i+1)) && nodesAsStringList.get(i+2).length() == 1 && CharacterLists.OPERATORS.contains(nodesAsStringList.get(i+2).charAt(0))) {
                // Create nodes and remove the second number and the operator
                Node leftvalue = new Value(Double.parseDouble(nodesAsStringList.get(i)));
                Node rightvalue = new Value(Double.parseDouble(nodesAsStringList.remove(i+1)));
                Operator operator = getOperator(nodesAsStringList.remove(i+1), leftvalue, rightvalue);
                // Replace the first number with the solution | (1, 2, *) --> (2)
                nodesAsStringList.set(i, String.valueOf(operator.getValue()));
                // Start over with the array
                i = -1;
            }
        }
        return new Value(Double.parseDouble(nodesAsStringList.get(0)));
    }

    private void replaceVariable() {
        for (int i = 0; i < nodesAsStringList.size(); i++) {
            if (CharacterLists.VARIABLES.contains(nodesAsStringList.get(i).charAt(0))) {
                nodesAsStringList.set(i, variableReplacement);
            }
        }
    }

    private Operator getOperator(String s, Node leftvalue, Node rightvalue) {
        // Return the right operator from string, with the values from leftvalue and rightvalue
        return switch (s) {
            case "+" -> new Add(leftvalue, rightvalue);
            case "-" -> new Subtract(leftvalue, rightvalue);
            case "*" -> new Multiply(leftvalue, rightvalue);
            case "/" -> new Divide(leftvalue, rightvalue);
            default -> new Power(leftvalue, rightvalue);
        };
    }

}
