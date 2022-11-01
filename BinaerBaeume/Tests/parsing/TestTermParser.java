package parsing;

import org.junit.jupiter.api.Test;
import parsing.exceptions.IllegalCharacterAfterNumberException;
import parsing.exceptions.IllegalCharacterException;
import parsing.exceptions.TwoCharactesInARowException;
import parsing.exceptions.WrongBracketsException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTermParser {

    @Test
    void testGetArr() {
        TermParser termParser = new TermParser("+");
        assertEquals("+", termParser.parse()[0]);
    }

    @Test
    void testEasy() {
        TermParser termParser = new TermParser("100\n\t / 2");
        termParser.parse();
        assertEquals("[100][/][2]", termParser.toString());
    }

    @Test
    void testComplicated() {
        TermParser termParser = new TermParser("100* 3 + 2.34*2,3");
        termParser.parse();
        assertEquals("[100][*][3][+][2.34][*][2.3]", termParser.toString());
    }

    @Test
    void testVorzeichen() {
        TermParser termParser = new TermParser("1 +- 1");
        termParser.parse();
        assertEquals("[1][+][-1]", termParser.toString());
    }

    @Test
    void testBrackets() {
        TermParser termParser = new TermParser("(1+1.3  + 2)");
        termParser.parse();
        assertEquals("[(][1][+][1.3][+][2][)]", termParser.toString());
    }

    @Test
    void testMultipleBrackets() {
        TermParser termParser = new TermParser("(1 + 1.3 + 2 + (3 + 4))");
        termParser.parse();
        assertEquals("[(][1][+][1.3][+][2][+][(][3][+][4][)][)]", termParser.toString());
    }

    @Test
    void testArrayIsNull() {
        TermParser termParser = new TermParser("1+1");
        assertEquals("The array is null", termParser.toString());
    }

    @Test
    void testParseIllegalArgumentException() {
        TermParser termParser = new TermParser("1 + 1L");
        termParser.parse();
        String error = new IllegalCharacterException('L').getMessage();
        assertEquals(error, termParser.toString());
    }

    @Test
    void testParseTwoOperatorsInARow() {
        TermParser termParser = new TermParser("1 ++ 1");
        termParser.parse();
        String error = new TwoCharactesInARowException(new char[] {'+', '+'}).getMessage();
        assertEquals(error, termParser.toString());
    }

    @Test
    void testParseTwoCharactersInARow() {
        TermParser termParser = new TermParser("1 + 1..3");
        termParser.parse();
        String error = new TwoCharactesInARowException(new char[] {'.', '.'}).getMessage();
        assertEquals(error, termParser.toString());
    }

    @Test
    void testParseTwoDifferentIllegallCharactersInARow() {
        TermParser termParser = new TermParser("1 + 1.,2");
        termParser.parse();
        String error = new TwoCharactesInARowException(new char[] {'.', '.'}).getMessage();
        assertEquals(error, termParser.toString());
    }

    @Test
    void testIllegalOperatorAfterNumber() {
        TermParser termParser = new TermParser("1 + 1.");
        termParser.parse();
        String error = new IllegalCharacterAfterNumberException('.').getMessage();
        assertEquals(error, termParser.toString());
    }

    @Test
    void testIllegalBracketClosingBracketBeforeOpeningBracket() {
        TermParser termParser = new TermParser(")1 + 1");
        termParser.parse();
        String error = new WrongBracketsException().getMessage();
        assertEquals(error, termParser.toString());
    }

    @Test
    void testIllegalBracketNoClosingBracketAfterOpeningBracket() {
        TermParser termParser = new TermParser("(1 + 1");
        termParser.parse();
        String error = new WrongBracketsException().getMessage();
        assertEquals(error, termParser.toString());
    }

    @Test
    void testParseIllegallCharactersAndIllegalOPeratorInARow() {
        TermParser termParser = new TermParser("1 +. 1.2");
        termParser.parse();
        String error = new TwoCharactesInARowException(new char[] {'+', '.'}).getMessage();
        assertEquals(error, termParser.toString());
    }

    @Test
    void testCharaterAfterKeyOperator() {
        TermParser termParser = new TermParser("1 + -.5");
        termParser.parse();
        String error = new TwoCharactesInARowException(new char[] {'+', '-'}).getMessage();
        assertEquals(error, termParser.toString());
    }

}
