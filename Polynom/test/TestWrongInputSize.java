import exceptions.WrongInputSizeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestWrongInputSize {

    @Test
    void testInputToLong() {
        Polynom polynom = new Polynom(new double[] {4, 5, 2, 8, 9, 10});
        assertEquals(new WrongInputSizeException(6).getMessage(), polynom.toString());
    }

    @Test
    void testInputToShort() {
        Polynom polynom = new Polynom(new double[] {4, 5, 2, 8});
        assertEquals(new WrongInputSizeException(4).getMessage(), polynom.toString());
    }

}