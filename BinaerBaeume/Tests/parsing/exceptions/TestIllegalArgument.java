package parsing.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import parsing.InfixToPostfix;

public class TestIllegalArgument {

    @Test
    void testIllegalArgument() {
        String error = new IllegalCharacterException(new StringBuilder("1+1L")).getMessage();
        InfixToPostfix infixToPostfix = new InfixToPostfix("1 + 1L");
        infixToPostfix.parse();
        assertEquals(error, infixToPostfix.toString());
    }

    @Test
    void testIllegallArgumentBetweenLegalArguments() {
        String error = new IllegalCharacterException(new StringBuilder("1+L")).getMessage();
        InfixToPostfix infixToPostfix = new InfixToPostfix("1 +L 1");
        infixToPostfix.parse();
        System.out.println("-----Illegal character-----\n" + error + "\n");
        assertEquals(error, infixToPostfix.toString());
    }

    @Test
    void testIllegallArgumentAtStart() {
        String error = new IllegalCharacterException(new StringBuilder("P")).getMessage();
        InfixToPostfix infixToPostfix = new InfixToPostfix("P1 + 1");
        infixToPostfix.parse();
        assertEquals(error, infixToPostfix.toString());
    }

}
