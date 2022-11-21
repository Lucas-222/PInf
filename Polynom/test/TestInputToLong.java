import exceptions.InputToLongException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestInputToLong {

    @Test
    void testInputToLong() {
        Polynom polynom = new Polynom(new double[] {4, 5, 2, 8, 9, 10});
        assertEquals(new InputToLongException().getMessage(), polynom.toString());
    }

}