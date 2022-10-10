package parsing;

public class IllegallArgumentException extends Exception {

    public IllegallArgumentException() {}

    @Override
    public String getMessage() {
        return "Illegal argument found";
    }

}
