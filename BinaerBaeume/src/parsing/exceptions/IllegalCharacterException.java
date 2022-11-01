package parsing.exceptions;

import java.util.ArrayList;

public class IllegalCharacterException extends Exception {
    ArrayList<Character> chars;
    private final char c;

    public IllegalCharacterException(ArrayList<Character> chars, char c) {
        this.chars = chars;
        this.c = c;
    }

    @Override
    public String getMessage() {
        StringBuilder stringBuilder = new StringBuilder();
        for (char v : chars) {
            stringBuilder.append(v);
        }

        return "after charcters: " + stringBuilder + " Illegal character (" + c + ") found";
    }

}
