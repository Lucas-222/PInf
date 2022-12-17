import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import exceptions.WrongInputSizeException;

public class TestExtremePoints {

    @Test
    void testQuadratic() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { -3.0, 2.0, 1.0, 0.0, 0.0 });
        // Because the degree of the polynom is 2 minima and maxima are the same
        assertEquals(-1.0, polynom.getMinima().get(0).getXValue());
        assertEquals(-4.0, polynom.getMinima().get(0).getYValue());

        assertEquals(-1.0, polynom.getMaxima().get(0).getXValue());
        assertEquals(-4.0, polynom.getMaxima().get(0).getYValue());
    }

    @Test
    void testCubic() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 0.0, -7.0, 3.0, 1.0/3.0, 0.0 });
        // Minima
        assertEquals(1.0, polynom.getMinima().get(0).getXValue());
        assertEquals(-3.6666666666666665, polynom.getMinima().get(0).getYValue());
        // Maxima
        assertEquals(-7.0, polynom.getMaxima().get(0).getXValue());
        assertEquals(81.66666666666667, polynom.getMaxima().get(0).getYValue());
    }

}
