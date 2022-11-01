package parsing.exceptions;

public class IllegalCharacterAfterNumberException extends Exception {
    private char c;

    public IllegalCharacterAfterNumberException(char c) {
        this.c = c;
    }

    @Override
    public String getMessage() {
        return "Illegal character ("+ c +") after number found";
    }

}
