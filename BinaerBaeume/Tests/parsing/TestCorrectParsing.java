package parsing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCorrectParsing {

    @Test
    void testGetArr() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("+");
        assertEquals("+", infixToPostfix.parse()[0]);
    }

    @Test
    void testEasy() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("100\n\t / 2");
        infixToPostfix.parse();
        assertEquals("[100][/][2]", infixToPostfix.toString());
    }

    @Test
    void testComplicated() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("100* 3 + 2.34*2,3");
        infixToPostfix.parse();
        assertEquals("[100][*][3][+][2.34][*][2.3]", infixToPostfix.toString());
    }

    @Test
    void testVorzeichen() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("1 +- 1");
        infixToPostfix.parse();
        assertEquals("[1][+][-1]", infixToPostfix.toString());
    }

    @Test
    void testVorzeichenAtTheStart() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("-1 + 1");
        infixToPostfix.parse();
        assertEquals("[-1][+][1]", infixToPostfix.toString());
    }

    @Test
    void testMinus() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("1 - 1");
        infixToPostfix.parse();
        assertEquals("[1][-][1]", infixToPostfix.toString());
    }

    @Test
    void testBrackets() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("(1+1.3  + 2)");
        infixToPostfix.parse();
        assertEquals("[(][1][+][1.3][+][2][)]", infixToPostfix.toString());
    }

    @Test
    void testMultipleBrackets() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("(1 + 1.3 + 2 + (3 + 4))");
        infixToPostfix.parse();
        assertEquals("[(][1][+][1.3][+][2][+][(][3][+][4][)][)]", infixToPostfix.toString());
    }

    @Test
    void testArrayIsNull() {
        InfixToPostfix infixToPostfix = new InfixToPostfix("1+1");
        assertEquals("The array is null", infixToPostfix.toString());
    }

    @Test
    void testIsNumberInt() {
        assertTrue(CharacterLists.isNumber("120"));
    }

    @Test
    void testIsNumberDouble() {
        assertTrue(CharacterLists.isNumber("120.0"));
    }

}
