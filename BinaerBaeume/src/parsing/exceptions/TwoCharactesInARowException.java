package parsing.exceptions;

public class TwoCharactesInARowException extends Exception {
    private char[] c;

    public TwoCharactesInARowException(char[] c) {
        this.c = c;
    }

    @Override
    public String getMessage() {
        return "Illegal characters ("+ c[0] + ", " + c[1] +") found";
    }

}
