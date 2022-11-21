package exceptions;

public class InputToLongException extends Exception {

    public InputToLongException() {

    }

    @Override
    public String getMessage() {
        return "The array is to long";
    }

}
