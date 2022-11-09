package parsing.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import parsing.InfixToPostfix;

public class TestWrongBrackets {

    @Test
    void testClosingBracketBeforeOpeningBracket() {
        String error = new WrongBracketsException(new StringBuilder(")")).getMessage();
        InfixToPostfix infixToPostfix = new InfixToPostfix(")1 + 1");
        infixToPostfix.parse();
        assertEquals(error, infixToPostfix.toString());
    }

    @Test
    void testCorrectAmountOfBracketsButWrongPlaced() {
        String error = new WrongBracketsException(new StringBuilder(")")).getMessage();
        InfixToPostfix infixToPostfix = new InfixToPostfix(")1 + 1(");
        infixToPostfix.parse();
        assertEquals(error, infixToPostfix.toString());
    }

    @Test
    void testBracketsInMiddle() {
        String error = new WrongBracketsException(new StringBuilder("1+)")).getMessage();
        InfixToPostfix infixToPostfix = new InfixToPostfix("1 +) 1(");
        infixToPostfix.parse();
        assertEquals(error, infixToPostfix.toString());
    }

    @Test
    void testBracketsType2() {
        String error = new WrongBracketsException(new StringBuilder(")")).getMessage();
        InfixToPostfix infixToPostfix = new InfixToPostfix(")1 + 1(");
        infixToPostfix.parse();
        System.out.println("-----Wrong placement of brackets-----\n" + error + "\n");
        assertEquals(error, infixToPostfix.toString());
    }

    @Test
    void testBracketsType1() {
        String error = new WrongBracketsException(1).getMessage();
        InfixToPostfix infixToPostfix = new InfixToPostfix("(1 + 1()");
        infixToPostfix.parse();
        System.out.println("-----Wrong amount of brackets-----\n" + error + "\n");
        assertEquals(error, infixToPostfix.toString());
    }

    @Test
    void testNoClosingBracketAfterOpeningBracket() {
        String error = new WrongBracketsException(1).getMessage();
        InfixToPostfix infixToPostfix = new InfixToPostfix("(1 + 1");
        infixToPostfix.parse();
        assertEquals(error, infixToPostfix.toString());
    }

}
