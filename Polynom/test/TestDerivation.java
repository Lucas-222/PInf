import exceptions.WrongInputSizeException;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class TestDerivation {

    @Test
    void testDerivation0() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 3.0, 0.0, 0.0, 0.0, 0.0 });
        String expected = Arrays.toString(new double[] { 0, 0, 0, 0, 0 });
        String actual = Arrays.toString(polynom.derivationCoefficients());
        assertEquals(expected, actual);
    }

    @Test
    void testDerivation1() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 3.0, 2.0, 0.0, 0.0, 0.0 });
        String expected = Arrays.toString(new double[] { 2, 0, 0, 0, 0 });
        String actual = Arrays.toString(polynom.derivationCoefficients());
        assertEquals(expected, actual);
    }

    @Test
    void testDerivation2() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 3.0, 2.0, 4.0, 0.0, 0.0 });
        String expected = Arrays.toString(new double[] { 2, 8, 0, 0, 0 });
        String actual = Arrays.toString(polynom.derivationCoefficients());
        assertEquals(expected, actual);
    }

    @Test
    void testDerivation3() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 3.0, 2.0, 4.0, 3.0, 0.0 });
        String expected = Arrays.toString(new double[] { 2, 8, 9, 0, 0 });
        String actual = Arrays.toString(polynom.derivationCoefficients());
        assertEquals(expected, actual);
    }

    @Test
    void testDerivation4() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 3.0, 2.0, 4.0, 3.0, 2.0 });
        String expected = Arrays.toString(new double[] { 2, 8, 9, 8, 0 });
        String actual = Arrays.toString(polynom.derivationCoefficients());
        assertEquals(expected, actual);
    }

    @Test
    void testDerivationMinus() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 3.0, 2.0, 4.0, -3.0, 0.0 });
        String expected = Arrays.toString(new double[] { 2, 8, -9, 0, 0 });
        String actual = Arrays.toString(polynom.derivationCoefficients());
        assertEquals(expected, actual);
    }

    @Test
    void testDerivationHoles() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 3.0, 0.0, 4.0, 0.0, 2.0 });
        assertEquals("f'(x) = 8x^3 + 8x", polynom.derivationPolynom().toString());
    }

    @Test
    void testDerivationPolynomMinus() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 3.0, 2.0, -4.0, 3.0, 2.0 });
        assertEquals("f'(x) = 8x^3 + 9x^2 - 8x + 2", polynom.derivationPolynom().toString());
    }

    @Test
    void testDerivationLoop() throws WrongInputSizeException {
        Polynom start = new Polynom(new double[] { 1.632344, 0.0, -3.694, 1.0, 0.0 });
        System.out.println("Start: " + start);

        for (int i = 0; i <= start.getDegree()+1; i++) {
            Polynom temp = start.derivationPolynom();
            System.out.println(temp);
            start = temp;
        }

        System.out.println(start.derivationPolynom());

    }

}
