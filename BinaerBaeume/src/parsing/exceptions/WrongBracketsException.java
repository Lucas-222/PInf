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
        if (errorType == 1) {
            return "Wrong amount of brackets found\nNote: There always needs to be the same amount of opening brackets and closing brackets";
        }
        char errorChar = stringBuilder.charAt(stringBuilder.length()-1);
        return "After characters " + stringBuilder.deleteCharAt(stringBuilder.length()-1) + " wrong bracket " + errorChar + " found";
    }

}
