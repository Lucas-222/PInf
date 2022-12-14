import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import exceptions.WrongInputSizeException;

public class TestPolynomCalculations {

    @Test
    void testDivision() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { -6.0, 5.0, -3.0, 4.0, 0.0 });
        Polynom secondPolynom = new Polynom(new double[] { -1.0, 1.0, 0.0, 0.0, 0.0 });
        Polynom expected = new Polynom(new double[] { 6.0, 1.0, 4.0, 0.0, 0.0 });
        assertEquals(expected.toString(), polynom.divide(secondPolynom).toString());
    }

    @Test
    void testAddition() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { -6.0, 5.0, -3.0, 4.0, 0.0 });
        Polynom secondPolynom = new Polynom(new double[] { -1.0, 1.0, 0.0, 0.0, 0.0 });
        Polynom expected = new Polynom(new double[] { -7.0, 6.0, -3.0, 4.0, 0.0 });
        assertEquals(expected.toString(), polynom.add(secondPolynom).toString());
    }

    @Test
    void testSubstraction() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { -6.0, 5.0, -3.0, 4.0, 0.0 });
        Polynom secondPolynom = new Polynom(new double[] { -1.0, 1.0, 0.0, 0.0, 0.0 });
        Polynom expected = new Polynom(new double[] { -5.0, 4.0, -3.0, 4.0, 0.0 });
        assertEquals(expected.toString(), polynom.substract(secondPolynom).toString());
    }

    @Test
    void testMultiplication() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 1.0, 2.0, 3.0, 4.0, 0.0 });
        Polynom secondPolynom = new Polynom(new double[] { 1.0, 2.0, 0.0, 0.0, 0.0 });
        Polynom expected = new Polynom(new double[] { 1.0, 4.0, 7.0, 10.0, 8.0 });
        assertEquals(expected.toString(), polynom.multiply(secondPolynom).toString());
    }
}
