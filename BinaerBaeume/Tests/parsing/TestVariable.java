package parsing;

import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestVariable {

    @Test
    void testSimpleParsing() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("1 * x", "1");
        assertEquals("[1][*][x]", infixToPostfix.toString());
    }

    @Test
    void testBigParsing() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("1 * x + 3 * 5", "1");
        assertEquals("[1][*][x][+][3][*][5]", infixToPostfix.toString());
    }

    @Test
    void testPostfix() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("3 * x", "4");
        String[] expectedStringArr = new String[] {"[3, x, *]", "12.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(infixToPostfix.postfix()));
    }

    @Test
    void testPostfixMultipleVariables() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("3 * x * (x * 3)", "4");
        String[] expectedStringArr = new String[] {"[3, x, *, x, 3, *, *], 144.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(infixToPostfix.postfix()));
    }

    @Test
    void testLoop() {
        for (double i = 1; i <= 100; i+= 1) {
            InfixToPostfix infixToPostfix = new InfixToPostfix("x * 2 * 2000 + (x * 30.45) / x ^ 3", String.valueOf(i));
            System.out.println(Arrays.toString(infixToPostfix.postfix()));
        }
    }

}
