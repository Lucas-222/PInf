package parsing.exceptions;

public class TwoCharactesInARowException extends Exception {
    StringBuilder stringBuilder;

    public TwoCharactesInARowException(StringBuilder stringbuilder) {
        this.stringBuilder = stringbuilder;
    }

    @Override
    public String getMessage() {
        char[] errorChars = new char[] {stringBuilder.charAt(stringBuilder.length()-1), stringBuilder.charAt(stringBuilder.length()-2)};
        stringBuilder.delete(stringBuilder.length()-2, stringBuilder.length());

        return "After characters: " + stringBuilder + " illegal characters (" + errorChars[0] + ") and (" + errorChars[1] + ") found\nNote: Two characters or operators in a row are not allowed except if it is (-)";
    }

}
