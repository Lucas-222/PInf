package parsing.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import parsing.TermParser;

public class TestIllegalCharacterAfterNumber {

    @Test
    void testCharacterAfterNumber() {
        String error = new IllegalCharacterAfterNumberException(new StringBuilder("1+1.")).getMessage();
        TermParser termParser = new TermParser("1 + 1.");
        termParser.parse();
        System.out.println("-----Character after number-----\n" + error + "\n");
        assertEquals(error, termParser.toString());
    }


}
