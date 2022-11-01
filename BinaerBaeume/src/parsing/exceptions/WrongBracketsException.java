package parsing.exceptions;

public class WrongBracketsException extends Exception {

    public WrongBracketsException() {
    }

    @Override
    public String getMessage() {
        return "Wrong amount of brackets found";
    }

}
