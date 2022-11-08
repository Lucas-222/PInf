package parsing.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import parsing.TermParser;

public class TestWrongBrackets {

    @Test
    void testClosingBracketBeforeOpeningBracket() {
        String error = new WrongBracketsException(new StringBuilder(")")).getMessage();
        TermParser termParser = new TermParser(")1 + 1");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

    @Test
    void testCorrectAmountOfBracketsButWrongPlaced() {
        String error = new WrongBracketsException(new StringBuilder(")")).getMessage();
        TermParser termParser = new TermParser(")1 + 1(");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

    @Test
    void testBracketsInMiddle() {
        String error = new WrongBracketsException(new StringBuilder("1+)")).getMessage();
        TermParser termParser = new TermParser("1 +) 1(");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

    @Test
    void testBracketsType2() {
        String error = new WrongBracketsException(new StringBuilder(")")).getMessage();
        TermParser termParser = new TermParser(")1 + 1(");
        termParser.parse();
        System.out.println("-----Wrong placement of brackets-----\n" + error + "\n");
        assertEquals(error, termParser.toString());
    }

    @Test
    void testBracketsType1() {
        String error = new WrongBracketsException(1).getMessage();
        TermParser termParser = new TermParser("(1 + 1()");
        termParser.parse();
        System.out.println("-----Wrong amount of brackets-----\n" + error + "\n");
        assertEquals(error, termParser.toString());
    }

    @Test
    void testNoClosingBracketAfterOpeningBracket() {
        String error = new WrongBracketsException(1).getMessage();
        TermParser termParser = new TermParser("(1 + 1");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

}
