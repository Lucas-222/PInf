package parsing.exceptions;

import org.junit.jupiter.api.Test;
import parsing.InfixToPostfix;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestIllegalVariable {

    @Test
    void testIllegalPlacedVariable() {
        String error = new IllegalVariableException(new StringBuilder("1+x1"), 1).getMessage();
        InfixToPostfix infixToPostfix = new InfixToPostfix("1 + x 1", "4");
        System.out.println("-----Wrong placement of variable-----\n" + error + "\n");
        assertEquals(error, infixToPostfix.toString());
    }

    @Test
    void testNoReplacementVariableFound() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("3 * x * (x * 3)");
        String[] expectedStringArr = new String[] {new IllegalVariableException(new StringBuilder("1+x1"), 2).getMessage()};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(infixToPostfix.postfix()));
    }

    @Test
    void testNoValidReplacement() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("3 * x", ")");
        String[] expectedStringArr = new String[] {new WrongBracketsException(new StringBuilder("3*)")).getMessage()};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(infixToPostfix.postfix()));
    }

}
