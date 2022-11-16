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

    public Node calcNode2(int start) {
        Node tempRightValue = null;
        Node operator = null;
        Node leftvalue = null;
        Node rightvalue = null;

        for (int i = start; i < nodesAsStringList.size(); i++) {
            if (nodesAsStringList.get(i).equals(")")) {
                for (int j = start; j < i+1; j++) {
                    System.out.println("removed: " + nodesAsStringList.remove(start));
                }
                return operator;
            }

            if (leftvalue == null) {
                leftvalue = new Value(Double.parseDouble(nodesAsStringList.get(i)));
            }

            if (nodesAsStringList.get(i).length() == 1 && CharacterLists.OPERATORS.contains(nodesAsStringList.get(i).charAt(0))) {
                if (i+1 != nodesAsStringList.size() && nodesAsStringList.get(i+1).equals("(")) {
                    nodesAsStringList.remove(i+1);
                    rightvalue = calcNode2(i+1);
                }

                if (i+3 <= nodesAsStringList.size()) {
                    if (test(i) || isPower(nodesAsStringList.get(i+2))) {
                        if (i + 5 <= nodesAsStringList.size() && nodesAsStringList.get(i + 4).equals("^")) {
                            tempRightValue = getRightValue(nodesAsStringList.get(i + 4), nodesAsStringList.get(i + 3), nodesAsStringList.get(i + 5));
                            System.out.println("Wenn das hier kommt problem: " + nodesAsStringList.remove(i + 3));
                            nodesAsStringList.remove(i + 3);
                            nodesAsStringList.remove(i + 3);
                        }

                        if (tempRightValue == null && !nodesAsStringList.get(i+2).equals("(") && !nodesAsStringList.get(i+3).equals("(")) {
                            rightvalue = getRightValue(nodesAsStringList.get(i + 2), nodesAsStringList.get(i + 1), nodesAsStringList.get(i + 3));
                        } else {
                            rightvalue = getRightValue(nodesAsStringList.get(i + 2), nodesAsStringList.get(i + 1), tempRightValue);
                            tempRightValue = null;
                        }
                        System.out.println("Woanders removed: " + nodesAsStringList.remove(i + 1));
                        System.out.println("Woanders removed: " + nodesAsStringList.remove(i + 1));
                    } else {
                        if (rightvalue == null) {
                            rightvalue = new Value(Double.parseDouble(nodesAsStringList.get(i + 1)));
                        }
                        operator = getOperator(nodesAsStringList.get(i), leftvalue, rightvalue);
                        System.out.println("i: " + nodesAsStringList.get(i));
                        System.out.println("left: " + leftvalue);
                        System.out.println("right: " + rightvalue);
                        System.out.println("operator: " + operator);
                        System.out.println("value: " + operator.getValue() + "\n");
                        leftvalue = operator;
                        rightvalue = null;
                    }
                } else {
                    if (rightvalue == null) {
                        rightvalue = new Value(Double.parseDouble(nodesAsStringList.get(i + 1)));
                    }
                    operator = getOperator(nodesAsStringList.get(i), leftvalue, rightvalue);
                    System.out.println("i: " + nodesAsStringList.get(i));
                    System.out.println("left: " + leftvalue);
                    System.out.println("right: " + rightvalue);
                    System.out.println("operator: " + operator);
                    System.out.println("value: " + operator.getValue() + "\n");
                    leftvalue = operator;
                    rightvalue = null;
                }
            }
        }

        assert operator != null;
        return operator;
    }

    public boolean test(int i) {
        return isMultiplication(nodesAsStringList.get(i+2)) && !isMultiplication(nodesAsStringList.get(i));
    }

    public boolean isMultiplication(String s) {
        return s.equals("*") || s.equals("/") || s.equals("^");
    }

    public boolean isPower(String s) {
        return s.equals("^");
    }

    public Node getRightValue(String operator, String leftvalue, String rightvalue) {
        return getOperator(operator, new Value(Double.parseDouble(leftvalue)), new Value(Double.parseDouble(rightvalue)));
    }

    public Node getRightValue(String operator, String leftvalue, Node rightvalue) {
        return getOperator(operator, new Value(Double.parseDouble(leftvalue)), rightvalue);
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
