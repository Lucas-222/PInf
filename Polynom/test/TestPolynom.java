import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestPolynom {

    @Test
    void testInput0() {
        Polynom polynom = new Polynom(new double[] {3, 0, 0, 0, 0});
        assertEquals("f(x) = 3.0", polynom.toString());
    }

    @Test
    void testInput1() {
        Polynom polynom = new Polynom(new double[] {3, 10, 0, 0, 0});
        assertEquals("f(x) = 10.0x + 3.0", polynom.toString());
    }

    @Test
    void testInput2() {
        Polynom polynom = new Polynom(new double[] {4, 5, 2, 0, 0});
        assertEquals("f(x) = 2.0x^2 + 5.0x + 4.0", polynom.toString());
    }

    @Test
    void testInput3() {
        Polynom polynom = new Polynom(new double[] {4, 5, 2, 7, 0});
        assertEquals("f(x) = 7.0x^3 + 2.0x^2 + 5.0x + 4.0", polynom.toString());
    }

    @Test
    void testInput4() {
        Polynom polynom = new Polynom(new double[] {4, 5, 2, 8, 9});
        assertEquals("f(x) = 9.0x^4 + 8.0x^3 + 2.0x^2 + 5.0x + 4.0", polynom.toString());
    }

    @Test
    void testMinus() {
        Polynom polynom = new Polynom(new double[] {4, 5, -2, 8, 9});
        assertEquals("f(x) = 9.0x^4 + 8.0x^3 - 2.0x^2 + 5.0x + 4.0", polynom.toString());
    }

    @Test
    void testMinusAtTheStart() {
        Polynom polynom = new Polynom(new double[] {4, 5, -2, 0, 0});
        assertEquals("f(x) = -2.0x^2 + 5.0x + 4.0", polynom.toString());
    }

    @Test
    void testHoles1() {
        Polynom polynom = new Polynom(new double[] {0, 1, -2, 0, 0});
        assertEquals("f(x) = -2.0x^2 + 1.0x", polynom.toString());
    }

    @Test
    void testHoles2() {
        Polynom polynom = new Polynom(new double[] {1, 0, -2, 0, 0});
        assertEquals("f(x) = -2.0x^2 + 1.0", polynom.toString());
    }

    @Test
    void testDegree() {
        Polynom polynom = new Polynom(new double[] {1, 0, 2, 0, 0});
        assertEquals(2, polynom.getDegree());
    }

    @Test
    void testPunktsymmetrie() {
        Polynom polynom = new Polynom(new double[] {0, 1, 0, 4, 0});
        assertEquals(polynom.SYMMETRIES[1], polynom.getSymmetry());
    }

    @Test
    void testAchsensymmetrie() {
        Polynom polynom = new Polynom(new double[] {1, 0, 2, 0, 0});
        assertEquals(polynom.SYMMETRIES[0], polynom.getSymmetry());
    }

    @Test
    void testKeinSymmetrie() {
        Polynom polynom = new Polynom(new double[] {1, 4, 2, 0, 0});
        assertEquals(polynom.SYMMETRIES[2], polynom.getSymmetry());
    }

}
