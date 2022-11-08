package parsing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCorrectParsing {

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

}
