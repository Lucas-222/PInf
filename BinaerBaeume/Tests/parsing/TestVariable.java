package parsing;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestVariable {

    @Test
    void testSimpleParsing() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("1 * x", "1");
        infixToPostfix.parse();
        assertEquals("[1][*][x]", infixToPostfix.toString());
    }

    @Test
    void testBigParsing() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("1 * x + 3 * 5", "1");
        infixToPostfix.parse();
        assertEquals("[1][*][x][+][3][*][5]", infixToPostfix.toString());
    }

    @Test
    void testParsing() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("3 * x", "4");
        String[] expectedStringArr = new String[] {"[3, x, *]", "12.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(infixToPostfix.postfix()));
    }

    @Test
    void testParsingBig() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("3 * x * (x * 3)", "4");
        String[] expectedStringArr = new String[] {"[3, x, *, x, 3, *, *], 144.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(infixToPostfix.postfix()));
    }

}
