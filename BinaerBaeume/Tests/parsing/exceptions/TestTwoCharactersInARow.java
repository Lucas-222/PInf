package parsing.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import parsing.InfixToPostfix;

public class TestTwoCharactersInARow {

    @Test
    void testTwoOperatorsInARow() {
        String error = new TwoCharactesInARowException(new StringBuilder("1++")).getMessage();
        InfixToPostfix infixToPostfix = new InfixToPostfix("1 ++ 1");
        infixToPostfix.parse();
        assertEquals(error, infixToPostfix.toString());
    }

    @Test
    void testTwoCharactersInARow() {
        String error = new TwoCharactesInARowException(new StringBuilder("1+1..")).getMessage();
        InfixToPostfix infixToPostfix = new InfixToPostfix("1 + 1..3");
        infixToPostfix.parse();
        assertEquals(error, infixToPostfix.toString());
    }

    @Test
    void testCharaterAfterKeyOperator() {
        String error = new TwoCharactesInARowException(new StringBuilder("1+-.")).getMessage();
        InfixToPostfix infixToPostfix = new InfixToPostfix("1 + -.5");
        infixToPostfix.parse();
        assertEquals(error, infixToPostfix.toString());
    }

    @Test
    void testTwoCharactersInARowAtTheStart() {
        String error = new TwoCharactesInARowException(new StringBuilder("..")).getMessage();
        InfixToPostfix infixToPostfix = new InfixToPostfix("..1 + 1..3");
        infixToPostfix.parse();
        assertEquals(error, infixToPostfix.toString());
    }

    @Test
    void testIllegalCharacterAndIllegalOperatorInARow() {
        String error = new TwoCharactesInARowException(new StringBuilder("1+.")).getMessage();
        InfixToPostfix infixToPostfix = new InfixToPostfix("1 +. 1.2");
        infixToPostfix.parse();
        assertEquals(error, infixToPostfix.toString());
    }

    @Test
    void testTwoDifferentIllegallCharactersInARow() {
        String error = new TwoCharactesInARowException(new StringBuilder("1+1..")).getMessage();
        InfixToPostfix infixToPostfix = new InfixToPostfix("1 + 1.,2");
        infixToPostfix.parse();
        System.out.println("-----Two characters in a row-----\n" + error + "\n");
        assertEquals(error, infixToPostfix.toString());
    }

}
