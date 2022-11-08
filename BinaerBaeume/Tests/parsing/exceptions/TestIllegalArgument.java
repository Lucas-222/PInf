package parsing.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import parsing.TermParser;

public class TestIllegalArgument {

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
        System.out.println("-----Illegal character-----\n" + error + "\n");
        assertEquals(error, termParser.toString());
    }

    @Test
    void testIllegallArgumentAtStart() {
        String error = new IllegalCharacterException(new StringBuilder("P")).getMessage();
        TermParser termParser = new TermParser("P1 + 1");
        termParser.parse();
        assertEquals(error, termParser.toString());
    }

}
