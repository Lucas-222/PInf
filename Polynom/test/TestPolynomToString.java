import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import exceptions.WrongInputSizeException;

public class TestPolynomToString {

    @Test
    void testInput0() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 3.0, 0.0, 0.0, 0.0, 0.0 });
        assertEquals("f(x) = 3", polynom.toString());
    }

    @Test
    void testInput1() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 3.0, 10.0, 0.0, 0.0, 0.0 });
        assertEquals("f(x) = 10x + 3", polynom.toString());
    }

    @Test
    void testInput2() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 4.0, 5.0, 2.0, 0.0, 0.0 });
        assertEquals("f(x) = 2x^2 + 5x + 4", polynom.toString());
    }

    @Test
    void testInput3() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 4.0, 5.0, 2.0, 7.0, 0.0 });
        assertEquals("f(x) = 7x^3 + 2x^2 + 5x + 4", polynom.toString());
    }

    @Test
    void testInput4() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 4.0, 5.0, 2.0, 8.0, 9.0 });
        assertEquals("f(x) = 9x^4 + 8x^3 + 2x^2 + 5x + 4", polynom.toString());
    }

    @Test
    void testMinus() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 4.0, 5.0, -2.0, 8.0, 9.0 });
        assertEquals("f(x) = 9x^4 + 8x^3 - 2x^2 + 5x + 4", polynom.toString());
    }

    @Test
    void testMinusAtTheStart() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 4.0, 5.0, -2.0, 0.0, 0.0 });
        assertEquals("f(x) = -2x^2 + 5x + 4", polynom.toString());
    }

    @Test
    void testHoles1() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 0.0, 1.0, -2.0, 0.0, 0.0 });
        assertEquals("f(x) = -2x^2 + x", polynom.toString());
    }

    @Test
    void testHoles2() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[]{1.0, 0.0, -2.0, 0.0, 0.0});
        assertEquals("f(x) = -2x^2 + 1", polynom.toString());
    }

    @Test
    void testDegree() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 1.0, 0.0, 2.0, 0.0, 0.0 });
        assertEquals(2, polynom.getDegree());
    }

    @Test
    void testNoDegree() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 });
        assertEquals(0, polynom.getDegree());
    }

    @Test
    void testPunktsymmetrie() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 0.0, 1.0, 0.0, 4.0, 0.0 });
        assertTrue(polynom.isPointsymmetric());
    }

    @Test
    void testAchsensymmetrie() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 1.0, 0.0, 2.0, 0.0, 0.0 });
        assertTrue(polynom.isAxissymmetric());
    }

    @Test
    void testNoSymmetrie() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 1.0, 4.0, 2.0, 0.0, 0.0 });
        assertFalse(polynom.isPointsymmetric());
        assertFalse(polynom.isAxissymmetric());
    }

    @Test
    void testNullSymmetrie() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 });
        assertFalse(polynom.isPointsymmetric());
        assertFalse(polynom.isAxissymmetric());
    }

}
