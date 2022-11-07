package parsing.exceptions;

public class IllegalCharacterAfterNumberException extends Exception {
    StringBuilder stringBuilder;

    public IllegalCharacterAfterNumberException(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    @Override
    public String getMessage() {
        // Create a stringbuilder for the return
        StringBuilder returnString = new StringBuilder();
        // The error char is the last char in stringbuilder
        char errorChar = stringBuilder.charAt(stringBuilder.length()-1);
        // if the error char is the first character in stringbuilder
        if (stringBuilder.length() == 1) {
            returnString.append("At the start of the input");
        } else {
            returnString.append("After characters").append(stringBuilder.deleteCharAt(stringBuilder.length() - 1));
        }
        return returnString.append(" illegal character (").append(errorChar).append(") was found\nNote: (.) and (,) are not allowed as the last part of a number, try deleting it").toString();
    }

}
