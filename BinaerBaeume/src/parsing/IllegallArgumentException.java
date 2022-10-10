package parsing;

import java.util.ArrayList;

public class IllegallArgumentException extends Exception {

    public IllegallArgumentException() {}

    @Override
    public String getMessage() {
        return "Illegal argument found";
    }

}
