package parsing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTermParser {
    private final String error = "[Illegal argument found]";

    @Test
    void TestGetArr() {
        TermParser termParser = new TermParser("+");
        termParser.parse();
        assertEquals("+", termParser.getArr()[0]);
    }

    @Test
    void TestEasy() {
        TermParser termParser = new TermParser("100\n\t / 2");
        termParser.parse();
        assertEquals("[100][/][2]", termParser.toString());
    }

    @Test
    void TestComplicated() {
        TermParser termParser = new TermParser("100* 3 + 2.\n34*2,3");
        termParser.parse();
        assertEquals("[100][*][3][+][2.34][*][2.3]", termParser.toString());
    }

    @Test
    void TestVorzeichen() {
        TermParser termParser = new TermParser("1 +- 1");
        termParser.parse();
        assertEquals("[1][+][-1]", termParser.toString());
    }

    @Test
    void TestBrackets() {
        TermParser termParser = new TermParser("(1+1.3  + 2)");
        termParser.parse();
        assertEquals("[(][1][+][1.3][+][2][)]", termParser.toString());
    }

    @Test
    void TestMultipleBrackets() {
        TermParser termParser = new TermParser("(1 + 1.3 + 2 + (3 + 4))");
        termParser.parse();
        assertEquals("[(][1][+][1.3][+][2][+][(][3][+][4][)][)]", termParser.toString());
    }

    @Test
    void TestArrayIsNull() {
        TermParser termParser = new TermParser("1+1");
        assertEquals("The array is null", termParser.toString());
    }

    @Test
    void TestParseIllegalArgumentException() {
        TermParser termParser = new TermParser("1 + 1L");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

    @Test
    void TestParseTwoOperatorsInARow() {
        TermParser termParser = new TermParser("1 ++ 1");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

    @Test
    void TetParseTwoCharactersInARow() {
        TermParser termParser = new TermParser("1 + 1..3");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

    @Test
    void TestParseTwoDifferentIllegallCharactersInARow() {
        TermParser termParser = new TermParser("1 + 1.,2");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

    @Test
    void TestIllegalOperatorAfterNumber() {
        TermParser termParser = new TermParser("1 + 1.");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

    @Test
    void TestIllegalBracketClosingBracketBeforeOpeningBracket() {
        TermParser termParser = new TermParser(")1 + 1");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

    @Test
    void TestIllegalBracketNoClosingBracketAfterOpeningBracket() {
        TermParser termParser = new TermParser("(1 + 1");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

    @Test
    void TestParseIllegallCharactersAndIllegalOPeratorInARow() {
        TermParser termParser = new TermParser("1 +. 1.2");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

    @Test
    void TestCharaterAfterKeyOperator() {
        TermParser termParser = new TermParser("1 + -.5");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

}
