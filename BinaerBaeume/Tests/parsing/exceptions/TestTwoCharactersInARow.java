package parsing.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import parsing.TermParser;

public class TestTwoCharactersInARow {

    @Test
    void testTwoOperatorsInARow() {
        String error = new TwoCharactesInARowException(new StringBuilder("1++")).getMessage();
        TermParser termParser = new TermParser("1 ++ 1");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

    @Test
    void testTwoCharactersInARow() {
        String error = new TwoCharactesInARowException(new StringBuilder("1+1..")).getMessage();
        TermParser termParser = new TermParser("1 + 1..3");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

    @Test
    void testCharaterAfterKeyOperator() {
        String error = new TwoCharactesInARowException(new StringBuilder("1+-")).getMessage();
        TermParser termParser = new TermParser("1 + -.5");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

    @Test
    void testTwoCharactersInARowAtTheStart() {
        String error = new TwoCharactesInARowException(new StringBuilder("..")).getMessage();
        TermParser termParser = new TermParser("..1 + 1..3");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

    @Test
    void testIllegalCharacterAndIllegalOperatorInARow() {
        String error = new TwoCharactesInARowException(new StringBuilder("1+.")).getMessage();
        TermParser termParser = new TermParser("1 +. 1.2");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

    @Test
    void testTwoDifferentIllegallCharactersInARow() {
        String error = new TwoCharactesInARowException(new StringBuilder("1+1..")).getMessage();
        TermParser termParser = new TermParser("1 + 1.,2");
        termParser.parse();
        System.out.println("-----Two characters in a row-----\n" + error + "\n");
        assertEquals(error, termParser.toString());
    }

}
