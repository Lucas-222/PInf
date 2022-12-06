import exceptions.WrongInputSizeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestNull {

    @Test
    void testNullLinear() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] {5, 5, 0, 0, 0});
        String expected = "[-1.0]";
        String actual = polynom.getNull().toString();
        assertEquals(expected, actual);
        assertEquals(0, polynom.functionValue(polynom.getNull().get(0)));
    }

    @Test
    void testTwoNull() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] {-7, 6, 1, 0, 0});
        String expected = "[1.0, -7.0]";
        String actual = polynom.getNull().toString();
        assertEquals(expected, actual);
        assertEquals(0, polynom.functionValue(polynom.getNull().get(0)));
        assertEquals(0, polynom.functionValue(polynom.getNull().get(1)));
    }

    @Test
    void testOneNull() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] {1, -2, 1, 0, 0});
        String expected = "[1.0]";
        String actual = polynom.getNull().toString();
        assertEquals(expected, actual);
        assertEquals(0, polynom.functionValue(polynom.getNull().get(0)));
    }

    @Test
    void testZeroNull() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] {5, 5, 2, 0, 0});
        String expected = "[]";
        String actual = polynom.getNull().toString();
        assertEquals(expected, actual);
    }

    @Test
    void testCube() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] {5, 5, 2, 3, 0});
        String expected = "[]";
        String actual = polynom.getNull().toString();
        assertEquals(expected, actual);
    }

}
