package parsing.exceptions;

public class IllegalCharacterException extends Exception {
    private char c;

    public IllegalCharacterException(char c) {
        this.c = c;
    }

    @Override
    public String getMessage() {
        return "Illegal character ("+ c +") found";
    }

}
