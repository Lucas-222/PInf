package parsing;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPostfix {

    @Test
    void testAddition() {
        TermParser termParser = new TermParser("3 + 3");
        String[] expectedStringArr = new String[] {"[3, 3, +]", "6.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(termParser.postfix()));
    }

    @Test
    void testSubtraction() {
        TermParser termParser = new TermParser("3 - 3");
        String[] expectedStringArr = new String[] {"[3, 3, -]", "0.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(termParser.postfix()));
    }

    @Test
    void testMultiplication() {
        TermParser termParser = new TermParser("3 * 3");
        String[] expectedStringArr = new String[] {"[3, 3, *]", "9.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(termParser.postfix()));
    }

    @Test
    void testDivisionBig() {
        TermParser termParser = new TermParser("3 / 3");
        String[] expectedStringArr = new String[] {"[3, 3, /]", "1.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(termParser.postfix()));
    }

    @Test
    void testPower() {
        TermParser termParser = new TermParser("3 ^ 3");
        String[] expectedStringArr = new String[] {"[3, 3, ^]", "27.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(termParser.postfix()));
    }

    @Test
    void testAdditionBig() {
        TermParser termParser = new TermParser("3 + 3 + 3");
        String[] expectedStringArr = new String[] {"[3, 3, +, 3, +]", "9.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(termParser.postfix()));
    }

    @Test
    void testSubtractionBig() {
        TermParser termParser = new TermParser("3 - 3 - 3");
        String[] expectedStringArr = new String[] {"[3, 3, -, 3, -]", "-3.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(termParser.postfix()));
    }

    @Test
    void testMultiplicationBig() {
        TermParser termParser = new TermParser("3 * 3 * 3");
        String[] expectedStringArr = new String[] {"[3, 3, *, 3, *]", "27.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(termParser.postfix()));
    }

    @Test
    void testDivision() {
        TermParser termParser = new TermParser("10 / 5 / 2");
        String[] expectedStringArr = new String[] {"[10, 5, /, 2, /]", "1.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(termParser.postfix()));
    }

    @Test
    void testPowerBig() {
        TermParser termParser = new TermParser("2 ^ 2 ^ 2");
        String[] expectedStringArr = new String[] {"[2, 2, 2, ^, ^]", "16.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(termParser.postfix()));
    }

    @Test
    void testVorzeichen() {
        TermParser termParser = new TermParser("-3 * (-1 + 2)");
        String[] expectedStringArr = new String[] {"[-3, -1, 2, +, *]", "-3.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(termParser.postfix()));
        System.out.println("-----Postfix notation of [-3 * (-1 + 2)]-----\n" + Arrays.toString(termParser.postfix()) + "\n");
    }

    @Test
    void testSortMixed() {
        TermParser termParser = new TermParser("3 * 1 + 2 / 2 ^ 3 - 5");
        String[] expectedStringArr = new String[] {"[3, 1, *, 2, 2, 3, ^, /, +, 5, -]", "-1.75"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(termParser.postfix()));
    }

    @Test
    void testSortBrackets() {
        TermParser termParser = new TermParser("3 * (1 + 2)");
        String[] expectedStringArr = new String[] {"[3, 1, 2, +, *]", "9.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(termParser.postfix()));
    }

}
