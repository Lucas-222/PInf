import exceptions.WrongInputSizeException;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class TestDerivation {

    @Test
    void testDerivation0() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] {3, 0, 0, 0, 0});
        String expected = Arrays.toString(new double[] {0, 0, 0, 0, 0});
        String actual = Arrays.toString(polynom.derivationCoefficients());
        assertEquals(expected, actual);
    }

    @Test
    void testDerivation1() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] {3, 2, 0, 0, 0});
        String expected = Arrays.toString(new double[] {2, 0, 0, 0, 0});
        String actual = Arrays.toString(polynom.derivationCoefficients());
        assertEquals(expected, actual);
    }

    @Test
    void testDerivation2() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] {3, 2, 4, 0, 0});
        String expected = Arrays.toString(new double[] {2, 8, 0, 0, 0});
        String actual = Arrays.toString(polynom.derivationCoefficients());
        assertEquals(expected, actual);
    }

    @Test
    void testDerivation3() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] {3, 2, 4, 3, 0});
        String expected = Arrays.toString(new double[] {2, 8, 9, 0, 0});
        String actual = Arrays.toString(polynom.derivationCoefficients());
        assertEquals(expected, actual);
    }

    @Test
    void testDerivation4() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] {3, 2, 4, 3, 2});
        String expected = Arrays.toString(new double[] {2, 8, 9, 8, 0});
        String actual = Arrays.toString(polynom.derivationCoefficients());
        assertEquals(expected, actual);
    }

    @Test
    void testDerivationMinus() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] {3, 2, 4, -3, 0});
        String expected = Arrays.toString(new double[] {2, 8, -9, 0, 0});
        String actual = Arrays.toString(polynom.derivationCoefficients());
        assertEquals(expected, actual);
    }

    @Test
    void testDerivationHoles() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] {3, 0, 4, 0, 2});
        assertEquals("f(x) = 8.0x^3 + 8.0x", polynom.derivationPolynom().toString());
    }

    @Test
    void testDerivationPolynomMinus() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] {3, 2, -4, 3, 2});
        assertEquals("f(x) = 8.0x^3 + 9.0x^2 - 8.0x + 2.0", polynom.derivationPolynom().toString());
    }

}
