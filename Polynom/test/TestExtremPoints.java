import exceptions.WrongInputSizeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestExtremPoints {

    @Test
    void testMinima() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { -3.0, 0.0, 1.0, 0.0, 0.0 });
        assertEquals(-3.0, polynom.getMinima().getYValue());
        assertEquals(0.0, polynom.getMinima().getXValue());
    }

    @Test
    void testMaxima() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 3.0, 0.0, -1.0, 0.0, 0.0 });
        assertEquals(3.0, polynom.getMaxima().getYValue());
        assertEquals(0.0, polynom.getMaxima().getXValue());
    }

    @Test
    void testCubic() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 0.0, -7.0, 3.0, 1.0/3.0, 0.0 });
        // Minima
        assertEquals(-16, polynom.getMinima().getYValue());
        assertEquals(-3.0, polynom.getMinima().getXValue());
        // Maxima
        assertEquals(81.0+2.0/3.0, polynom.getMaxima().getYValue());
        assertEquals(-7.0, polynom.getMaxima().getXValue());
    }

}
