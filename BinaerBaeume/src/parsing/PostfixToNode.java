package parsing;

import termAsTreeInheritance.*;

import java.util.ArrayList;
import java.util.List;

public class PostfixToNode {
    private final List<String> nodesAsStringList;
    private String variableReplacement;
    private List<Node> nodes;

    public PostfixToNode(List<String> nodesAsStringList) {
        this.nodesAsStringList = nodesAsStringList;
    }

    public PostfixToNode(List<String> nodesAsStringList, String variableReplacement) {
        this.nodesAsStringList = nodesAsStringList;
        this.variableReplacement = variableReplacement;
    }

    public Node calcNode3(int start) {
        Node operator = null;
        Node leftvalue = null;
        Node rightvalue = null;
        System.out.println(nodesAsStringList);

        for (int i = start; i < nodesAsStringList.size(); i++) {
            if (leftvalue == null) {
                leftvalue = new Value(Double.parseDouble(nodesAsStringList.get(i)));
            }

            if (nodesAsStringList.get(i).equals(")")) {
                for (int j = start; j < i+1; j++) {
                    System.out.println("removed: " + nodesAsStringList.remove(start));
                }
                return operator;
            }

            if (nodesAsStringList.size() > 1 && nodesAsStringList.get(i).length() == 1 && CharacterLists.OPERATORS.contains(nodesAsStringList.get(i).charAt(0))) {
                if (i+1 != nodesAsStringList.size() && nodesAsStringList.get(i+1).equals("(")) {
                    nodesAsStringList.remove(i+1);
                    rightvalue = calcNode3(i+1);
                    operator = getOperator(nodesAsStringList.get(i), leftvalue, rightvalue);
                }

                if (i+1 < nodesAsStringList.size() && rightvalue == null) {
                    rightvalue = new Value(Double.parseDouble(nodesAsStringList.get(i+1)));
                }

                if (i+2 < nodesAsStringList.size()) {
                    if (i+5 < nodesAsStringList.size() && isPower(nodesAsStringList.get(i + 4))) {
                        // (x)(^)
                        rightvalue = getOperator(nodesAsStringList.get(i+4), new Value(Double.parseDouble(nodesAsStringList.get(i+3))), new Value(Double.parseDouble(nodesAsStringList.get(i+5))));
                        nodesAsStringList.remove(i+3);
                        nodesAsStringList.remove(i+3);
                        nodesAsStringList.remove(i+3);
                        rightvalue = getOperator(nodesAsStringList.get(i+2), new Value(Double.parseDouble(nodesAsStringList.get(i+1))), rightvalue);
                        operator = getOperator(nodesAsStringList.get(i), leftvalue, rightvalue);
                        leftvalue = operator;
                        output(operator, leftvalue, rightvalue, i);
                        rightvalue = null;
                        nodesAsStringList.remove(i+1);
                        nodesAsStringList.remove(i+1);
                    } else if (i+3 < nodesAsStringList.size() && isPower(nodesAsStringList.get(i + 2))) {
                        // (x)(^)
                        rightvalue = getOperator(nodesAsStringList.get(i+2), new Value(Double.parseDouble(nodesAsStringList.get(i+1))), new Value(Double.parseDouble(nodesAsStringList.get(i+3))));
                        nodesAsStringList.remove(i+1);
                        nodesAsStringList.remove(i+1);
                        nodesAsStringList.remove(i+1);
                        operator = getOperator(nodesAsStringList.get(i), leftvalue, rightvalue);
                        leftvalue = operator;
                        output(operator, leftvalue, rightvalue, i);
                        rightvalue = null;
                    } else if (!isMultiplication(nodesAsStringList.get(i)) && !isMultiplication(nodesAsStringList.get(i + 2))) {
                        // (+)(+)
                        operator = getOperator(nodesAsStringList.get(i), leftvalue, rightvalue);
                        leftvalue = operator;
                        output(operator, leftvalue, rightvalue, i);
                        rightvalue = null;
                    } else if (!isMultiplication(nodesAsStringList.get(i)) && isMultiplication(nodesAsStringList.get(i + 2))) {
                        // (+)(*)
                        if (nodesAsStringList.get(i + 3).equals("(")) {
                            nodesAsStringList.remove(i + 3);
                            rightvalue = calcNode3(i + 3);
                        } else {
                            rightvalue = new Value(Double.parseDouble(nodesAsStringList.get(i + 3)));
                        }
                        rightvalue = getOperator(nodesAsStringList.get(i + 2), new Value(Double.parseDouble(nodesAsStringList.get(i + 1))), rightvalue);
                        operator = getOperator(nodesAsStringList.get(i), leftvalue, rightvalue);
                        leftvalue = operator;
                        output(operator, leftvalue, rightvalue, i);
                        rightvalue = null;
                        nodesAsStringList.remove(i + 1);
                        nodesAsStringList.remove(i + 1);
                    } else if (isMultiplication(nodesAsStringList.get(i)) && !isMultiplication(nodesAsStringList.get(i + 2))) {
                        // (*)(+)
                        operator = getOperator(nodesAsStringList.get(i), leftvalue, rightvalue);
                        output(operator, leftvalue, rightvalue, i);
                        leftvalue = operator;
                        rightvalue = null;
                    } else if (isMultiplication(nodesAsStringList.get(i)) && isMultiplication(nodesAsStringList.get(i + 2))) {
                        // (*)(*)
                        operator = getOperator(nodesAsStringList.get(i), leftvalue, rightvalue);
                        output(operator, leftvalue, rightvalue, i);
                        leftvalue = operator;
                        rightvalue = null;
                    }
                } else {
                    operator = getOperator(nodesAsStringList.get(i), leftvalue, rightvalue);
                }
            }
        }
        return operator;
    }

    public void output(Node operator, Node leftvalue, Node rightvalue, int i) {
        System.out.println("i: " + nodesAsStringList.get(i));
        System.out.println("left: " + leftvalue);
        System.out.println("right: " + rightvalue);
        System.out.println("operator: " + operator);
        System.out.println("value: " + operator.getValue() + "\n");
    }

    /*public Node calcNode2(int start) {
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
    }*/

    public boolean isMultiplication(String s) {
        return s.equals("*") || s.equals("/") || s.equals("^");
    }

    public boolean isPower(String s) {
        return s.equals("^");
    }

    public Node calcNode() {
        Node rightvalue = null;
        Node leftvalue;
        Node test = null;

        replaceVariable();
        // Loop through every string in nodesAsString
        for (int i = 0; i < nodesAsStringList.size(); i++) {
            System.out.println(nodesAsStringList);
            // If there are two numbers before an operator | (1, 2, *)
            if (i+2 <= nodesAsStringList.size() && CharacterLists.isNumber(nodesAsStringList.get(i)) && CharacterLists.isNumber(nodesAsStringList.get(i+1)) && nodesAsStringList.get(i+2).length() == 1 && CharacterLists.OPERATORS.contains(nodesAsStringList.get(i+2).charAt(0))) {
                // Create nodes and remove the second number and the operator
                leftvalue = new Value(Double.parseDouble(nodesAsStringList.get(i)));
                rightvalue = new Value(Double.parseDouble(nodesAsStringList.get(i + 1)));

                test = getOperator(nodesAsStringList.get(i+2), leftvalue, rightvalue);

                nodesAsStringList.set(i, String.valueOf(test.getValue()));
                nodesAsStringList.remove(i+1);
                nodesAsStringList.remove(i+1);

                i = -1;
                System.out.println(test);
            }
        }
        System.out.println("\nErgebnis: " + test);
        return test;
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
