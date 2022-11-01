package parsing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import parsing.exceptions.*;
import java.util.ArrayList;

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
    void testIllegalArgument() {
        ArrayList<Character> characters = new ArrayList<>();
        characters.add('1');
        characters.add('+');
        characters.add('1');
        String error = new IllegalCharacterException(characters, 'L').getMessage();
        TermParser termParser = new TermParser("1 + 1L");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

    @Test
    void testIllegallArgumentBetweenLegalArguments() {
        ArrayList<Character> characters = new ArrayList<>();
        characters.add('1');
        characters.add('+');
        String error = new IllegalCharacterException(characters, 'L').getMessage();
        TermParser termParser = new TermParser("1 +L 1");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

    @Test
    void testTwoOperatorsInARow() {
        String error = new TwoCharactesInARowException(new char[] {'+', '+'}).getMessage();
        TermParser termParser = new TermParser("1 ++ 1");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

    @Test
    void testTwoCharactersInARow() {
        String error = new TwoCharactesInARowException(new char[] {'.', '.'}).getMessage();
        TermParser termParser = new TermParser("1 + 1..3");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

    @Test
    void testTwoDifferentIllegallCharactersInARow() {
        String error = new TwoCharactesInARowException(new char[] {'.', '.'}).getMessage();
        TermParser termParser = new TermParser("1 + 1.,2");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

    @Test
    void testIllegalOperatorAfterNumber() {
        String error = new IllegalCharacterAfterNumberException('.').getMessage();
        TermParser termParser = new TermParser("1 + 1.");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

    @Test
    void testClosingBracketBeforeOpeningBracket() {
        String error = new WrongBracketsException().getMessage();
        TermParser termParser = new TermParser(")1 + 1");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

    @Test
    void testCorrectAmountOfBracketsButWrongPlaced() {
        String error = new WrongBracketsException().getMessage();
        TermParser termParser = new TermParser(")1 + 1(");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

    @Test
    void testNoClosingBracketAfterOpeningBracket() {
        String error = new WrongBracketsException().getMessage();
        TermParser termParser = new TermParser("(1 + 1");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

    @Test
    void testIllegalCharacterAndIllegalOperatorInARow() {
        String error = new TwoCharactesInARowException(new char[] {'+', '.'}).getMessage();
        TermParser termParser = new TermParser("1 +. 1.2");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

    @Test
    void testCharaterAfterKeyOperator() {
        String error = new TwoCharactesInARowException(new char[] {'+', '-'}).getMessage();
        TermParser termParser = new TermParser("1 + -.5");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

}
