import exceptions.WrongInputSizeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestFunctionValue {

    @Test
    void testFunctionValue0() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 1.0, 3.0, 2.0, 0.0, 0.0 });
        assertEquals(3.0, polynom.functionValue(2.0));
    }

    @Test
    void testFunctionValue1() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 3.0, 4.0, 0.0, 0.0, 0.0 });
        assertEquals(11.0, polynom.functionValue(2.0));
    }

    @Test
    void testFunctionValue2() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 10.0, 5.0, 2.0, 0.0, 0.0 });
        assertEquals(28.0, polynom.functionValue(2.0));
    }

    @Test
    void testFunctionValue3() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 10.0, 5.0, 2.0, 1.0, 0.0 });
        assertEquals(36.0, polynom.functionValue(2.0));
    }

    @Test
    void testFunctionValue4() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 10.0, 5.0, 2.0, 1.0, 7.0 });
        assertEquals(148.0, polynom.functionValue(2.0));
    }

}
