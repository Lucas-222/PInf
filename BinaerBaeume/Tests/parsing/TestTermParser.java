package parsing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import parsing.exceptions.*;

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
    void testVorzeichenAtTheStart() {
        TermParser termParser = new TermParser("-1 + 1");
        termParser.parse();
        assertEquals("[-1][+][1]", termParser.toString());
    }

    @Test
    void testMinus() {
        TermParser termParser = new TermParser("1 - 1");
        termParser.parse();
        assertEquals("[1][-][1]", termParser.toString());
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
        String error = new IllegalCharacterException(new StringBuilder("1+1L")).getMessage();
        TermParser termParser = new TermParser("1 + 1L");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

    @Test
    void testIllegallArgumentBetweenLegalArguments() {
        String error = new IllegalCharacterException(new StringBuilder("1+L")).getMessage();
        TermParser termParser = new TermParser("1 +L 1");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

    @Test
    void testVisualisingIllegalCharacter() {
        String error = new IllegalCharacterException(new StringBuilder("1+1-10*1Ü")).getMessage();
        TermParser termParser = new TermParser("1+1-10*1Ü10+5");
        termParser.parse();
        System.out.println("-----Illegal character-----\n" + error + "\n");
        assertEquals(error, termParser.toString());
    }

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
    void testTwoDifferentIllegallCharactersInARow() {
        String error = new TwoCharactesInARowException(new StringBuilder("1+1..")).getMessage();
        TermParser termParser = new TermParser("1 + 1.,2");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

    @Test
    void testVisualisingTwoCharactersInARow() {
        String error = new TwoCharactesInARowException(new StringBuilder("1+1..")).getMessage();
        TermParser termParser = new TermParser("1 + 1.,2");
        termParser.parse();
        System.out.println("-----Two characters in a row-----\n" + error + "\n");
        assertEquals(error, termParser.toString());
    }

    @Test
    void testIllegalOperatorAfterNumber() {
        String error = new IllegalCharacterAfterNumberException(new StringBuilder("1+1.")).getMessage();
        TermParser termParser = new TermParser("1 + 1.");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

    @Test
    void testVisualisingCharacterAfterNumber() {
        String error = new IllegalCharacterAfterNumberException(new StringBuilder("1+1.")).getMessage();
        TermParser termParser = new TermParser("1 + 1.");
        termParser.parse();
        System.out.println("-----Character after number-----\n" + error + "\n");
        assertEquals(error, termParser.toString());
    }


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
    void testVisualisingBracketsType2() {
        String error = new WrongBracketsException(new StringBuilder(")")).getMessage();
        TermParser termParser = new TermParser(")1 + 1(");
        termParser.parse();
        System.out.println("-----Wrong placement of brackets-----\n" + error + "\n");
        assertEquals(error, termParser.toString());
    }

    @Test
    void testVisualisingBracketsType1() {
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

    @Test
    void testIllegalCharacterAndIllegalOperatorInARow() {
        String error = new TwoCharactesInARowException(new StringBuilder("1+.")).getMessage();
        TermParser termParser = new TermParser("1 +. 1.2");
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
    void testSort() {
        TermParser termParser = new TermParser("3 * (1 + 2)");
        assertEquals("[3, 1, 2, +, *]", termParser.postfix());
    }

    @Test
    void testSortComplicated() {
        TermParser termParser = new TermParser("3 * (1 + 2) + 6.8 * 10 - 5");
        assertEquals("[3, 1, 2, +, *, 6.8, +, 10, *, 5, -]", termParser.postfix());
    }

    @Test
    void testSortVorzeichen() {
        TermParser termParser = new TermParser("-3 * (-1 + 2)");
        assertEquals("[-3, -1, 2, +, *]", termParser.postfix());
        System.out.println("-----Postfix notation of [-3 * (-1 + 2)]-----\n" + termParser.postfix() + "\n");
    }
}
