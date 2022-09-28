import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class TermParser {

    public static Node parse(String infixNotation) throws Exception {
        return StringToNodeParser.getOperatorFromString(parseToString(infixNotation));
    }

    public static String parseToString(String infixNotation) throws Exception {
        List<String> outputStack = new LinkedList<>();
        Stack<Character> operatorsStack = new Stack<>();
        char lastChar = infixNotation.charAt(0);

        //Change , to .
        infixNotation = infixNotation.replace(",", ".");

        for (int i = 0; i < infixNotation.length(); i++) {
            char currentChar = infixNotation.charAt(i);

            //Ignore Chars
            if (currentChar == '\n' || currentChar == '\t' || currentChar == '\r' || currentChar == ' ') {
                continue;
            }

            if (checkIsLegalChar(currentChar)) {
                //Nicht Erste Ziffer
                if (i != 0) {
                    //Check für doppelte operatoren
                    if (checkIsOperator(lastChar) && checkIsOperator(currentChar)) {
                        throw new Exception("Es wurden 2 operatoren hinter einander gefunden. Illegal");
                    }
                }

                //Check für Komma Zahlen
                if (currentChar == '.') {
                    //Vor dem Komma
                    Stack<String> beforeKomma = new Stack<>();
                    int j = outputStack.size() - 1;
                    while( !outputStack.isEmpty() &&  0 <= j && j < outputStack.size() && checkIsNumber(outputStack.get(j).charAt(0))) {
                        beforeKomma.push(outputStack.get(j));
                        outputStack.remove(j);
                        j--;
                    }

                    //Nach dem Komma
                    String afterKomma = "";
                    int k = i + 1;
                    while (k < infixNotation.length() && checkIsNumber(infixNotation.charAt(k))) {
                        afterKomma += infixNotation.charAt(k);
                        k++;
                    }

                    //Komma Zahl Bauen
                    String kommaZahl = "";
                    while (!beforeKomma.isEmpty()) {
                        kommaZahl += beforeKomma.pop();
                    }
                    kommaZahl += ".";
                    kommaZahl += afterKomma;

                    //Komma Zahl zum Output stack
                    outputStack.add(kommaZahl);

                    //Set Cur index to after Komma
                    i = k;
                    continue;
                }

                // Wenn Nummer
                if (checkIsNumber(currentChar)) {
                    int j = i + 1;
                    String number = String.valueOf(currentChar);
                    while (j >= 0 && j < infixNotation.length() && (checkIsNumber(infixNotation.charAt(j)) || infixNotation.charAt(j) == '.')) {
                        currentChar = infixNotation.charAt(j);
                        number += currentChar;
                        j++;
                    }
                    outputStack.add(number);
                    i = j - 1;
                    lastChar = currentChar;
                    continue;
                }

                //Argumenttrennzeichen
                if (currentChar == ')') {
                    char lastOperator;
                    do {
                        lastOperator = operatorsStack.pop();
                        if (lastOperator != '(') {
                            outputStack.add(String.valueOf(lastOperator));
                        }
                    } while ( lastOperator != '(' && !outputStack.isEmpty());

                    if (lastOperator != '(') {
                        throw new Exception("Keine Öffnende Klammer zur Schließenden gefunden");
                    }
                }

                //Current Char is Operator
                if (checkIsOperator(currentChar)) {
                    while (!operatorsStack.empty()
                            && checkIsOperator(operatorsStack.peek())
                            && checkIsLinksAssoziativ(currentChar)
                            && getPraezedenz(currentChar) <= getPraezedenz(operatorsStack.peek())) {
                        outputStack.add(String.valueOf(operatorsStack.pop()));
                    }
                    operatorsStack.push(currentChar);
                }

                if (currentChar == '(') {
                    operatorsStack.push(currentChar);
                }


                lastChar = currentChar;
            } else {
                throw new Exception("Charakter: ["+ currentChar +"] ist nicht Legal");
            }
        }

        while (!operatorsStack.isEmpty()) {
            outputStack.add(String.valueOf(operatorsStack.pop()));
        }

        return stackToString(outputStack);
    }

    private static String stackToString(List<String> charArr) throws Exception {
        Stack<StringNode> nodeStack = new Stack<>();
        for (int i = 0; i < charArr.size(); i++) {

            char curChar;
            if (charArr.get(i).length() <= 1) {
                //Nur ein Char
                curChar = charArr.get(i).charAt(0);
            } else {
                //Komma Zahl
                String kommaZahl = charArr.get(i);
                int kommaAnzahl = (int) kommaZahl.chars().filter((x) -> x == '.' ).count();
                if ( kommaAnzahl > 1 ) {
                    throw new Exception("Mehr als ein Komma in einer Zahl enthalten");
                }
                nodeStack.push(new StringValue(kommaZahl));
                continue;
            }

            //Ist Operator
            if(checkIsOperator(curChar)) {
                if( !nodeStack.isEmpty() && nodeStack.size() >= 2) {
                    //2 Values vorhanden
                    StringNode rightNode = nodeStack.pop();
                    StringNode leftNode = nodeStack.pop();
                    StringOperator stringOperator = new StringOperator(curChar);
                    stringOperator.rightNode = rightNode;
                    stringOperator.leftNode = leftNode;
                    nodeStack.push(stringOperator);
                }
            }

            if(checkIsNumber(curChar)) {
                nodeStack.push(new StringValue(String.valueOf(curChar)));
            }
        }

        return nodeStack.pop().toString();
    }

    private static boolean checkIsOperator(char character) {
        return character == '*' || character == '/' || character == '+' || character == '-' || character == '^';
    }

    private static boolean checkIsNumber(char character) {
        return character == '0' || character == '1' || character == '2' || character == '3' || character == '4' || character == '5' || character == '6' || character == '7' || character == '8' || character == '9';
    }

    private static boolean checkIsLinksAssoziativ(char character) {
        return character == '+' || character == '-' || character == '/' || character == '*';
    }

    private static int getPraezedenz(char character) {
        if (character == '+' || character == '-') {
            return 1;
        }
        if (character == '*' || character == '/') {
            return 2;
        }
        if (character == '^') {
            return 3;
        }
        return -1;
    }

    private static boolean checkIsLegalChar(char character) {
        //Ist Operator
        if (checkIsOperator(character)) {
            return true;
        }

        //Ist Zahl
        if ( checkIsNumber(character) ) {
            return true;
        }

        //Check is Klammer
        if (character == '(' || character == ')') {
            return true;
        }

        return character == '.';
    }

}

abstract class StringNode {
    public abstract String toString();
}
class StringValue extends StringNode{
    private String value;

    public StringValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
class StringOperator extends StringNode {
    public StringNode leftNode;
    public StringNode rightNode;
    private final String operator;

    public StringOperator(char operator) {
        this.leftNode = null;
        this.rightNode = null;
        this.operator = String.valueOf(operator);
    }

    public boolean isLeftEmpty() {
        return leftNode == null;
    }

    public boolean isRightEmpty() {
        return rightNode == null;
    }

    @Override
    public String toString() {
        return "(" + leftNode + "," + rightNode + ")" + operator;
    }
}