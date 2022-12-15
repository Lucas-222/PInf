import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import exceptions.WrongInputSizeException;

public class TestExtremePoints {

    @Test
    void testMinima() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { -3.0, 2.0, 1.0, 0.0, 0.0 });
        assertEquals(-4.0, polynom.getMinima().getYValue());
        assertEquals(-1.0, polynom.getMinima().getXValue());
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
        assertEquals(-3.6666666666666665, polynom.getMinima().getYValue());
        assertEquals(1.0, polynom.getMinima().getXValue());
        // Maxima
        assertEquals(81.66666666666667, polynom.getMaxima().getYValue());
        assertEquals(-7.0, polynom.getMaxima().getXValue());
    }

}