package parsing.exceptions;

public class IllegalVariableException extends Exception {
    // 1 is wrong placement, 2 is no replacement
    int errorType;
    StringBuilder stringBuilder;

    public IllegalVariableException(StringBuilder stringBuilder, int errorType) {
        this.stringBuilder = stringBuilder;
        this.errorType = errorType;
    }

    @Override
    public String getMessage() {
        if (errorType == 1) {
            char errorChar = stringBuilder.charAt(stringBuilder.length() - 1);
            return "After characters: " + stringBuilder.deleteCharAt(stringBuilder.length() - 1) + " illegal variable (" + errorChar + ") was found.";
        } else {
            return "No replacement variable found";
        }
    }
}
