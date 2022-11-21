package exceptions;

public class InputToLongException extends Exception {

    public InputToLongException() {

    }

    @Override
    public String getMessage() {
        return "The input is to long";
    }

}
