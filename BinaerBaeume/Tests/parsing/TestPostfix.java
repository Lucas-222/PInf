package parsing;

import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TestPostfix {

    @Test
    void testAddition() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("3 + 3");
        String[] expectedStringArr = new String[] {"[3, 3, +]", "6.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(infixToPostfix.postfix()));
    }

    @Test
    void testSubtraction() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("3 - 3");
        String[] expectedStringArr = new String[] {"[3, 3, -]", "0.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(infixToPostfix.postfix()));
    }

    @Test
    void testMultiplication() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("3 * 3");
        String[] expectedStringArr = new String[] {"[3, 3, *]", "9.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(infixToPostfix.postfix()));
    }

    @Test
    void testDivision() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("3 / 3");
        String[] expectedStringArr = new String[] {"[3, 3, /]", "1.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(infixToPostfix.postfix()));
    }

    @Test
    void testPower() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("3 ^ 3");
        String[] expectedStringArr = new String[] {"[3, 3, ^]", "27.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(infixToPostfix.postfix()));
    }

    @Test
    void testAdditionBig() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("3 + 3 + 3");
        String[] expectedStringArr = new String[] {"[3, 3, +, 3, +]", "9.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(infixToPostfix.postfix()));
    }

    @Test
    void testSubtractionBig() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("3 - 3 - 3");
        String[] expectedStringArr = new String[] {"[3, 3, -, 3, -]", "-3.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(infixToPostfix.postfix()));
    }

    @Test
    void testMultiplicationBig() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("3 * 3 * 3");
        String[] expectedStringArr = new String[] {"[3, 3, *, 3, *]", "27.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(infixToPostfix.postfix()));
    }

    @Test
    void testDivisionBig() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("10 / 5 / 2");
        String[] expectedStringArr = new String[] {"[10, 5, /, 2, /]", "1.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(infixToPostfix.postfix()));
    }

    @Test
    void testPowerBig() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("2 ^ 2 ^ 2 ^ 2");
        String[] expectedStringArr = new String[] {"[2, 2, 2, 2, ^, ^, ^]", "65536.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(infixToPostfix.postfix()));
    }

    @Test
    void testPowerBrackets() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("2 (^ 2 (^ 2 (^ 2)))");
        String[] expectedStringArr = new String[] {"[2, 2, 2, 2, ^, ^, ^]", "256.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(infixToPostfix.postfix()));
    }

    @Test
    void testVorzeichen() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("-3 * (-1 + 2)");
        String[] expectedStringArr = new String[] {"[-3, -1, 2, +, *]", "-3.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(infixToPostfix.postfix()));
    }

    @Test
    void testVorzeichenPower() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("1 - -3 ^ 2");
        String[] expectedStringArr = new String[] {"[1, -3, 2, ^, -]", "-8.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(infixToPostfix.postfix()));
    }

    @Test
    void testSortMixed() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("3 * 1 + 2 / 2 ^ 3 - 5");
        String[] expectedStringArr = new String[] {"[3, 1, *, 2, 2, 3, ^, /, +, 5, -], -1.75"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(infixToPostfix.postfix()));
    }

    @Test
    void testSortMixedBig() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("3 * (1 + 200) / 2 ^ 3 - 5 * 10 * 6 - 90 + 2.35 * (40000 + 3) * (4 * (5 + 6))");
        String expectedString = "[[3, 1, 200, +, *, 2, 3, ^, /, 5, 10, *, 6, *, -, 90, -, 2.35, 40000, 3, +, *, 4, 5, 6, +, *, *, +], 4135995.575]";
        String actualString = Arrays.toString(infixToPostfix.postfix());
        assertEquals(expectedString, actualString);
        System.out.println("-----Postfix notation and solution of [3 * (1 + 200) / 2 ^ 3 - 5 * 10 * 6 - 90 + 2,35 * (40000 + 3) * (4 * (5 + 6))]-----\n" + actualString + "\n");
    }

    @Test
    void testSortMixedd() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("3 * (1 + 4) - 2 + (5 / 5)");
        String[] expectedStringArr = new String[] {"[3, 1, 4, +, *, 2, -, 5, 5, /, +], 14.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(infixToPostfix.postfix()));
    }

    @Test
    void testSortBrackets() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("3 * (1 + 2)");
        String[] expectedStringArr = new String[] {"[3, 1, 2, +, *]", "9.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(infixToPostfix.postfix()));
    }

    @Test
    void testSortBracketsWithNumbersAfter() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("3 * (1 + 2) * 3");
        String[] expectedStringArr = new String[] {"[3, 1, 2, +, *, 3, *], 27.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(infixToPostfix.postfix()));
    }

    @Test
    void testSortMultipleBrackets() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("3 * (1 * (2 * (3 * (4 + 2))))");
        String[] expectedStringArr = new String[] {"[3, 1, 2, 3, 4, 2, +, *, *, *, *], 108.0"};
        assertEquals(Arrays.toString(expectedStringArr), Arrays.toString(infixToPostfix.postfix()));
    }

}
