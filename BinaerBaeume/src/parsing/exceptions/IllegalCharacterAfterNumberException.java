package parsing.exceptions;

public class IllegalCharacterAfterNumberException extends Exception {
    private final char c;

    public IllegalCharacterAfterNumberException(char c) {
        this.c = c;
    }

    @Override
    public String getMessage() {
        return "Illegal character ("+ c +") after number found";
    }

}
