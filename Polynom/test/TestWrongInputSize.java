import exceptions.WrongInputSizeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestWrongInputSize {

    @Test
    void testInputToLong() {
        try {
            new Polynom(new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 });
        } catch (WrongInputSizeException e) {
            assertEquals(new WrongInputSizeException(6).getMessage(), e.getMessage());
        }
    }

    @Test
    void testInputToShort() {
        try {
            new Polynom(new double[] { 1.0, 1.0, 1.0, 1.0 });
        } catch (WrongInputSizeException e) {
            assertEquals(new WrongInputSizeException(4).getMessage(), e.getMessage());
        }
    }

}
