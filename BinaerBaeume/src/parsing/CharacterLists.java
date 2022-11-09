package parsing;

import java.util.List;

public class CharacterLists {
    public static final List<Character> OPERATORS = List.of('+', '-', '*', '/', '^');
    public static final List<Character> CHARACTERS = List.of('(', ')', '.', ',');
    public static final List<Character> NUMBERS = List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
    public static final List<Character> OPERATORSANDCHARACTERS = List.of('+', '-', '*', '/', '^', '.', ',');

    public static boolean isNumber(String s) {
        // Because a number can contain more than one char, a loop needs to be performed
        for (int i = 0; i < s.length(); i++) {
            // If the string contains any number -> return true
            if (NUMBERS.contains(s.charAt(i))) {
                return true;
            }
        }
        // Defaut -> return false
        return false;
    }

}
