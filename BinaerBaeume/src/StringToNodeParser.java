import java.util.*;

public class StringToNodeParser {
    private static Map<String, String> objToStringMap = new HashMap<>();

    public static Node getOperatorFromString(String inputString) {
        SortedSet<Integer> tempBraces = new TreeSet<>();
        ArrayList<String> splittetBraces = new ArrayList<>();
        Map<String, String> merkMap = new HashMap<>();
        objToStringMap = new HashMap<>();

        for (int j = 0; j < inputString.length(); j++) {
            char currentChar = inputString.charAt(j);

            if (currentChar == '(')
            {
                //Wenn aktueller "(" ist
                tempBraces.add(j);
            }
            else if (currentChar == ')')
            {
                //Wenn aktueller ")" ist
                if (!tempBraces.isEmpty()) {
                    //Wenn ( vorgemerkt
                    int lastIndexLeftBrace = tempBraces.last();
                    tempBraces.remove(tempBraces.last());

                    String newAddition = inputString.substring(lastIndexLeftBrace, j + 2);
                    //Check if already exists
                    for (String allreadyIn: splittetBraces) {
                        newAddition = newAddition.replace(allreadyIn, merkMap.get(allreadyIn));
                    }

                    int merkInt = merkMap.size()+1;
                    String objName = "OBJ" + merkInt;
                    merkMap.put(newAddition, objName);
                    objToStringMap.put(objName, newAddition);
                    splittetBraces.add(newAddition);
                }
            }
        }
        String[] splittetInput = splittetBraces.toArray(new String[0]);
        return getNodeFromString(splittetInput[splittetInput.length-1]);
    }

    private static Node getNodeFromString(String nodeString) {
        int nodeStringKommaIndex = nodeString.indexOf(",");
        int nodeStringEndIndex = nodeString.indexOf(")");
        String leftNodeString = nodeString.substring(1, nodeStringKommaIndex);
        String rightNodeString = nodeString.substring(nodeStringKommaIndex + 1, nodeStringEndIndex);
        Operators nodeOperator = getOperatorsFromString(nodeString.substring(nodeString.length()-1));
        Node leftNode = null, rightNode = null;
        Operator returnOperator = null;


        if (leftNodeString.contains("OBJ")) {
            //Left Node ist ein OBJ
            String objString = objToStringMap.get(leftNodeString);
            leftNode = getNodeFromString(objString);
        } else {
            //Left Node ist kein OBJ
            try {
                leftNode = new Value(Double.parseDouble(leftNodeString));
            } catch (Exception e) {
                System.err.println("Error Trying to get Left Node:\n" + e);
            }
        }


        if (rightNodeString.contains("OBJ")) {
            //Right Node ist ein OBJ
            String objString = objToStringMap.get(rightNodeString);
            rightNode = getNodeFromString(objString);
        } else {
            //Right Node ist kein OBJ
            try {
                rightNode = new Value(Double.parseDouble(rightNodeString));
            } catch (Exception e) {
                System.err.println("Error Trying to get Right Node:\n" + e);
            }
        }

        try {
            if (leftNode != null && rightNode != null)
                returnOperator = new Operator(leftNode, rightNode, nodeOperator);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return returnOperator;
    }

    private static Operators getOperatorsFromString(String operator) {
        return switch (operator) {
            case "+" -> Operators.ADD;
            case "-" -> Operators.SUBTRACT;
            case "^" -> Operators.POWER;
            case "*" -> Operators.MULTIPLY;
            case "/" -> Operators.DIVIDE;
            default -> null;
        };
    }
}