package parsing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPostfix {

    @Test
    void testSort() {
        TermParser termParser = new TermParser("3 * 1 + 2");
        assertEquals("[3, 1, *, 2, +]", termParser.postfix());
    }

    @Test
    void testSortBrackets() {
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

    @Test
    void testStringToOperator() {
        TermParser termParser = new TermParser("1 + 1");
        assertEquals("[1, 1, +]", termParser.postfix());
    }

    @Test
    void testStringToOperatorComplicated() {
        TermParser termParser = new TermParser("1 + 1 + 1");
        assertEquals("[1, 1, +, 1, +]", termParser.postfix());
    }

    @Test
    void testStringToOperatorComplicated2() {
        TermParser termParser = new TermParser("1 + 1 + 1 + 1");
        assertEquals("[1, 1, +, 1, +, 1, +]", termParser.postfix());
    }
}
