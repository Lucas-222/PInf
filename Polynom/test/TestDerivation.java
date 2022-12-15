import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import exceptions.WrongInputSizeException;

public class TestDerivation {

    @Test
    void testDerivation0() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 3.0, 0.0, 0.0, 0.0, 0.0 });
        String expected = Arrays.toString(new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 });
        String actual = Arrays.toString(polynom.derivationCoefficients());
        assertEquals(expected, actual);
    }

    @Test
    void testDerivation1() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 3.0, 2.0, 0.0, 0.0, 0.0 });
        String expected = Arrays.toString(new double[] { 2.0, 0.0, 0.0, 0.0, 0.0 });
        String actual = Arrays.toString(polynom.derivationCoefficients());
        assertEquals(expected, actual);
    }

    @Test
    void testDerivation2() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 3.0, 2.0, 4.0, 0.0, 0.0 });
        String expected = Arrays.toString(new double[] { 2.0, 8.0, 0.0, 0.0, 0.0 });
        String actual = Arrays.toString(polynom.derivationCoefficients());
        assertEquals(expected, actual);
    }

    @Test
    void testDerivation3() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 3.0, 2.0, 4.0, 3.0, 0.0 });
        String expected = Arrays.toString(new double[] { 2.0, 8.0, 9.0, 0.0, 0.0 });
        String actual = Arrays.toString(polynom.derivationCoefficients());
        assertEquals(expected, actual);
    }

    @Test
    void testDerivation4() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 3.0, 2.0, 4.0, 3.0, 2.0 });
        String expected = Arrays.toString(new double[] { 2.0, 8.0, 9.0, 8.0, 0.0 });
        String actual = Arrays.toString(polynom.derivationCoefficients());
        assertEquals(expected, actual);
    }

    @Test
    void testDerivationMinus() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 3.0, 2.0, 4.0, -3.0, 0.0 });
        String expected = Arrays.toString(new double[] { 2.0, 8.0, -9.0, 0.0, 0.0 });
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
    void testMultipleDerivations() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 4.0, 5.0, 9.0, 3.0, 2.0 });
        assertEquals("f(x) = 2x^4 + 3x^3 + 9x^2 + 5x + 4", polynom.toString());
        Polynom firstDerivation = polynom.derivationPolynom();
        assertEquals("f'(x) = 8x^3 + 9x^2 + 18x + 5", firstDerivation.toString());
        Polynom secondDerivation = firstDerivation.derivationPolynom();
        assertEquals("f''(x) = 24x^2 + 18x + 18", secondDerivation.toString());
        Polynom thirdDerivation = secondDerivation.derivationPolynom();
        assertEquals("f'''(x) = 48x + 18", thirdDerivation.toString());
        Polynom fourthDerivation = thirdDerivation.derivationPolynom();
        assertEquals("f''''(x) = 48", fourthDerivation.toString());
        Polynom fifthDerivation = fourthDerivation.derivationPolynom();
        assertEquals("f'''''(x) = ", fifthDerivation.toString());
    }

    @Test
    void testDerivationLoop() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 1.632344, 0.0, -3.694, 1.0, 0.0 });
        System.out.println("Start: " + polynom);

        for (int i = 0; i <= polynom.getDegree()+1; i++) {
            Polynom temp = polynom.derivationPolynom();
            System.out.println(temp);
            polynom = temp;
        }

        System.out.println(polynom.derivationPolynom());

    }

}
