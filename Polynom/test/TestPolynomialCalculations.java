import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import exceptions.WrongInputSizeException;

public class TestPolynomialCalculations {

    @Test
    void testMultiplication() throws WrongInputSizeException {
        Polynom firstPolynom = new Polynom(new double[] { 1.0, 2.0, 3.0, 4.0, 0.0 });
        Polynom secondPolynom = new Polynom(new double[] { 1.0, 2.0, 0.0, 0.0, 0.0 });
        Polynom expected = new Polynom(new double[] { 1.0, 4.0, 7.0, 10.0, 8.0 });
        assertEquals(expected.toString(), PolynomialCalculations.multiply(firstPolynom, secondPolynom).toString());
    }

    @Test
    void testDivision() throws WrongInputSizeException {
        Polynom firstPolynom = new Polynom(new double[] { -4.0, 2.0, 4.0, -2.0, 0.0 });
        Polynom secondPolynom = new Polynom(new double[] { -1.0, 1.0, 0.0, 0.0, 0.0 });
        Polynom expected = new Polynom(new double[] { 4.0, 2.0, -2.0, 0.0, 0.0 });
        assertEquals(expected.toString(), PolynomialCalculations.divide(firstPolynom, secondPolynom).toString());
    }

    @Test
    void testAddition() throws WrongInputSizeException {
        Polynom firstPolynom = new Polynom(new double[] { -6.0, 5.0, -3.0, 4.0, 0.0 });
        Polynom secondPolynom = new Polynom(new double[] { -1.0, 1.0, 0.0, 0.0, 0.0 });
        Polynom expected = new Polynom(new double[] { -7.0, 6.0, -3.0, 4.0, 0.0 });
        assertEquals(expected.toString(), PolynomialCalculations.add(firstPolynom, secondPolynom).toString());
    }

    @Test
    void testSubstraction() throws WrongInputSizeException {
        Polynom firstPolynom = new Polynom(new double[] { -6.0, 5.0, -3.0, 4.0, 0.0 });
        Polynom secondPolynom = new Polynom(new double[] { -1.0, 1.0, 0.0, 0.0, 0.0 });
        Polynom expected = new Polynom(new double[] { -5.0, 4.0, -3.0, 4.0, 0.0 });
        assertEquals(expected.toString(), PolynomialCalculations.substract(firstPolynom, secondPolynom).toString());
    }

}
