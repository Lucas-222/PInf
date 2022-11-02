package parsing.exceptions;

public class IllegalCharacterAfterNumberException extends Exception {
    StringBuilder stringBuilder;

    public IllegalCharacterAfterNumberException(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    @Override
    public String getMessage() {
        char errorChar = stringBuilder.charAt(stringBuilder.length()-1);
        return "After characters " + stringBuilder.deleteCharAt(stringBuilder.length()-1) + " illegal character (" + errorChar + ") was found\nNote: (.) and (,) are not allowed as the last part of a number, try deleting it";
    }

}
