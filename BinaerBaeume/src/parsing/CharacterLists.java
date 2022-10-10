package parsing;

import java.util.ArrayList;

public class CharacterLists {
    public final ArrayList<Character> OPERATORS = fillListWithOperators();
    public final ArrayList<Character> CHARACTERS = fillListWithCharacters();
    public final ArrayList<Character> NUMBERS = fillListWithNumbers();

    public CharacterLists() {

    }

    private ArrayList<Character> fillListWithOperators() {
        ArrayList<Character> list = new ArrayList<>();
        list.add('+');
        list.add('-');
        list.add('*');
        list.add('/');
        list.add('^');
        return list;
    }

    private ArrayList<Character> fillListWithCharacters() {
        ArrayList<Character> list = new ArrayList<>();
        list.add('(');
        list.add(')');
        list.add(',');
        list.add('.');
        return list;
    }

    private ArrayList<Character> fillListWithNumbers() {
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


}
