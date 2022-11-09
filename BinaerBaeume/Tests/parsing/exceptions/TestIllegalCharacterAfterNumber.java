package parsing.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import parsing.InfixToPostfix;

public class TestIllegalCharacterAfterNumber {

    @Test
    void testCharacterAfterNumber() {
        String error = new IllegalCharacterAfterNumberException(new StringBuilder("1+1.")).getMessage();
        InfixToPostfix infixToPostfix = new InfixToPostfix("1 + 1.");
        infixToPostfix.parse();
        System.out.println("-----Character after number-----\n" + error + "\n");
        assertEquals(error, infixToPostfix.toString());
    }

}
