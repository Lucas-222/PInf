package parsing;

import java.util.List;

public class CharacterLists {
    public static final List<Character> OPERATORS = List.of('+', '-', '*', '/', '^');
    public static final List<Character> CHARACTERS = List.of('(', ')', '.', ',');
    public static final List<Character> NUMBERS = List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
    public static final List<Character> OPERATORSANDCHARACTERS = List.of('+', '-', '*', '/', '^', '.', ',');
}
