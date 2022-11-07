package parsing.exceptions;

public class IllegalCharacterException extends Exception {
    StringBuilder stringBuilder;

    public IllegalCharacterException(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    @Override
    public String getMessage() {
        // Create a stringbuilder for the return
        StringBuilder returnString = new StringBuilder();
        // ErrorChar is the last char in stringbuilder
        char errorChar = stringBuilder.charAt(stringBuilder.length()-1);
        // if the error char is the first character in stringbuilder
        if (stringBuilder.length() == 1) {
            returnString.append("At the start of the input");
        } else {
            returnString.append("After characters: ").append(stringBuilder.deleteCharAt(stringBuilder.length() - 1));
        }
        // ErrorChar gets deleted from stringbuilder, so it doesn't show twice
        return returnString.append(" illegal character (").append(errorChar).append(") found").toString();
    }

}
