import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import exceptions.WrongInputSizeException;

public class TestNull {

    @Test
    void testNullLinear() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 5.0, 5.0, 0.0, 0.0, 0.0 });
        String expected = "[-1.0]";
        String actual = polynom.getNull().toString();
        assertEquals(expected, actual);
        assertEquals(0.0, polynom.functionValue(polynom.getNull().get(0)));
    }

    @Test
    void testTwoNull() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { -7.0, 6.0, 1.0, 0.0, 0.0 });
        String expected = "[1.0, -7.0]";
        String actual = polynom.getNull().toString();
        assertEquals(expected, actual);
        assertEquals(0.0, polynom.functionValue(polynom.getNull().get(0)));
        assertEquals(0.0, polynom.functionValue(polynom.getNull().get(1)));
    }

    @Test
    void testOneNull() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 1.0, -2.0, 1.0, 0.0, 0.0 });
        String expected = "[1.0]";
        String actual = polynom.getNull().toString();
        assertEquals(expected, actual);
        assertEquals(0.0, polynom.functionValue(polynom.getNull().get(0)));
    }

    @Test
    void testZeroNull() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 5.0, 5.0, 2.0, 0.0, 0.0 });
        String expected = "[]";
        String actual = polynom.getNull().toString();
        assertEquals(expected, actual);
    }

    @Test
    void testCube() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 5.0, 5.0, 2.0, 3.0, 0.0 });
        String expected = "[]";
        String actual = polynom.getNull().toString();
        assertEquals(expected, actual);
    }

}
