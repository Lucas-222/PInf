package parsing;

import java.util.ArrayList;

public class CharacterLists {
    public final ArrayList<Character> OPERATORS = getOperators();
    public final ArrayList<Character> CHARACTERS = getCharacters();
    public final ArrayList<Character> NUMBERS = getNumbers();
    public final ArrayList<Character> OPERATORSANDCHARACTERS = getOperatorsAndCharacters();

    public CharacterLists() {}

    private ArrayList<Character> getOperators() {
        ArrayList<Character> list = new ArrayList<>();
        list.add('+');
        list.add('-');
        list.add('*');
        list.add('/');
        list.add('^');
        return list;
    }

    private ArrayList<Character> getCharacters() {
        ArrayList<Character> list = new ArrayList<>();
        list.add('(');
        list.add(')');
        list.add(',');
        list.add('.');
        return list;
    }

    private ArrayList<Character> getNumbers() {
        ArrayList<Character> list = new ArrayList<>();
        list.add('0');
        list.add('1');
        list.add('2');
        list.add('3');
        list.add('4');
        list.add('5');
        list.add('6');
        list.add('7');
        list.add('8');
        list.add('9');
        return list;
    }

    private ArrayList<Character> getOperatorsAndCharacters() {
        ArrayList<Character> list = new ArrayList<>();
        list.addAll(OPERATORS);
        list.addAll(CHARACTERS);
        return list;
    }

}
