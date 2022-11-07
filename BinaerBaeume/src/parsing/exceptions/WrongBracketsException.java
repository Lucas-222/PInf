package parsing.exceptions;

public class WrongBracketsException extends Exception {
    StringBuilder stringBuilder;
    // Type 1 is wrong amount, type 2 is wrong placement
    int errorType = 0;

    public WrongBracketsException(int errorType) {
        this.errorType = errorType;
    }

    public WrongBracketsException(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    @Override
    public String getMessage() {
        // If the amount of the brackets are wrong
        if (errorType == 1) {
            return "Wrong amount of brackets found\nNote: There always needs to be the same amount of opening brackets and closing brackets";
        }
        // Create a stringbuilder for the return
        StringBuilder returnString = new StringBuilder();
        // Error chars are the last two chars in stringbuilder
        char errorChar = stringBuilder.charAt(stringBuilder.length()-1);
        // if the error char is the first character in stringbuilder
        if (stringBuilder.length() == 1) {
            returnString.append("At the start of the input");
        } else {
            returnString.append("After characters ").append(stringBuilder.deleteCharAt(stringBuilder.length() - 1));
        }
        return returnString.append(" wrong bracket ").append(errorChar).append(" found").toString();
    }

}
