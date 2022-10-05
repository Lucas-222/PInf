import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTermParser {

    @Test
    void Testparse() {
        TermParser termParser = new TermParser("100\n\t / 2");
        termParser.parse();
        assertEquals("[100][/][2]", termParser.toString());
    }

    @Test
    void TestParseComplicated() {
        TermParser termParser = new TermParser("100* 3 + 2.\n34*2,3");
        termParser.parse();
        assertEquals("[100][*][3][+][2.34][*][2.3]", termParser.toString());
    }

    @Test
    void TestParseVorzeichen() {
        TermParser termParser = new TermParser("1 +- 1");
        termParser.parse();
        assertEquals("[1][+][-1]", termParser.toString());
    }

    @Test
    void TestParseBrackets() {
        TermParser termParser = new TermParser("(1+1.3  + 2)");
        termParser.parse();
        assertEquals("[(][1][+][1.3][+][2][)]", termParser.toString());
    }

    @Test
    void TestParseIllegalArgumentException() {
        TermParser termParser = new TermParser("1 + 1L");
        termParser.parse();
        assertEquals("Illegal argument found", termParser.toString());
    }

    @Test
    void TestParseTwoOperatorsInARow() {
        TermParser termParser = new TermParser("1 ++ 1");
        termParser.parse();
        assertEquals("Illegal argument found", termParser.toString());
    }

    @Test
    void TetParseTwoSameCharactersInARow() {
        TermParser termParser = new TermParser("1 + 1..3");
        termParser.parse();
        assertEquals("Illegal argument found", termParser.toString());
    }

}
