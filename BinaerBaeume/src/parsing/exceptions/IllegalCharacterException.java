package parsing.exceptions;

public class IllegalCharacterException extends Exception {
    StringBuilder stringBuilder;

    public IllegalCharacterException(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    @Override
    public String getMessage() {
        // ErrorChar is the last char in stringbuilder
        char errorChar = stringBuilder.charAt(stringBuilder.length()-1);
        // ErrorChar gets deleted from stringbuilder, so it doesn't show twice
        return "After characters: " + stringBuilder.deleteCharAt(stringBuilder.length()-1) + " illegal character (" + errorChar + ") found";
    }

}
