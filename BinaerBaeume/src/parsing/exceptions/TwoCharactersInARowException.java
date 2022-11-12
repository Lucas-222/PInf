package parsing.exceptions;

public class TwoCharactersInARowException extends Exception {
    StringBuilder stringBuilder;

    public TwoCharactersInARowException(StringBuilder stringbuilder) {
        this.stringBuilder = stringbuilder;
    }

    @Override
    public String getMessage() {
        // Create a stringbuilder for the return
        StringBuilder returnString = new StringBuilder();
        // Error chars are the last two chars in stringbuilder
        char[] errorChars = new char[] {stringBuilder.charAt(stringBuilder.length()-1), stringBuilder.charAt(stringBuilder.length()-2)};
        // if the error chars are the first characters in stringbuilder
        if (stringBuilder.length() == 2) {
            returnString.append("At the start of the input");
        } else {
            // Delete the last two chars from stringbuilder, so they don't show twice
            stringBuilder.delete(stringBuilder.length()-2, stringBuilder.length());
            returnString.append("After characters: ").append(stringBuilder);
        }
        return returnString.append(" illegal characters (").append(errorChars[0]).append(") and (").append(errorChars[1]).append(") found\nNote: Two characters or operators in a row are not allowed except if it is (-)").toString();
    }

}
