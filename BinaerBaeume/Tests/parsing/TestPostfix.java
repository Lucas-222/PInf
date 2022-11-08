package parsing;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPostfix {

    @Test
    void testSort() {
        TermParser termParser = new TermParser("3 * 1 + 2");
        String[] expectedStringArr = new String[] {"[3, 1, *, 2, +]", "5.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(termParser.postfix()));
    }

    @Test
    void testSortBrackets() {
        TermParser termParser = new TermParser("3 * (1 + 2)");
        String[] expectedStringArr = new String[] {"[3, 1, 2, +, *]", "9.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(termParser.postfix()));
    }

    @Test
    void testSortComplicated() {
        TermParser termParser = new TermParser("3 * (1 + 2) + 6.8 * 10 - 5");
        String[] expectedStringArr = new String[] {"[3, 1, 2, +, *, 6.8, +, 10, *, 5, -]", "72.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(termParser.postfix()));
    }

    @Test
    void testSortVorzeichen() {
        TermParser termParser = new TermParser("-3 * (-1 + 2)");
        String[] expectedStringArr = new String[] {"[-3, -1, 2, +, *]", "-3.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(termParser.postfix()));
        System.out.println("-----Postfix notation of [-3 * (-1 + 2)]-----\n" + Arrays.toString(termParser.postfix()) + "\n");
    }

    @Test
    void testStringToOperator() {
        TermParser termParser = new TermParser("1 + 1");
        String[] expectedStringArr = new String[] {"[1, 1, +]", "2.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(termParser.postfix()));
    }

    @Test
    void testStringToOperatorComplicated() {
        TermParser termParser = new TermParser("1 + 1 + 1");
        String[] expectedStringArr = new String[] {"[1, 1, +, 1, +]", "3.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(termParser.postfix()));
    }

    @Test
    void testStringToOperatorComplicated2() {
        TermParser termParser = new TermParser("1 + 1 + 1 + 1");
        String[] expectedStringArr = new String[] {"[1, 1, +, 1, +, 1, +]", "4.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(termParser.postfix()));
    }
}
