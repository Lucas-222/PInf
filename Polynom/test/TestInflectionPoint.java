import SpecialPoint.InflectionPoint;
import org.junit.jupiter.api.Test;
import exceptions.WrongInputSizeException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class TestInflectionPoint {

    @Test
    void testInflectionPointLinear() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 3.0, 2.0, 0.0, 0.0, 0.0 });
        assertEquals(new ArrayList<InflectionPoint>(), polynom.getInflectionPoints());
    }

    @Test
    void testInflectionPointQuadratic() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 3.0, 2.0, 5.0, 0.0, 0.0 });
        assertEquals(new ArrayList<InflectionPoint>(), polynom.getInflectionPoints());
    }

    @Test
    void testInflectionPointCubic() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 3.0, 2.0, 4.0, -5.0, 0.0 });
        // Minima
        assertEquals(-0.18548866554168453, polynom.getMinima().get(0).getXValue());
        assertEquals(2.798556506003667, polynom.getMinima().get(0).getYValue());
        // Inflectionpoint
        assertEquals(0.26666666666666666, polynom.getInflectionPoints().get(0).getXValue());
        assertEquals(3.722962962962963, polynom.getInflectionPoints().get(0).getYValue());
        assertFalse(polynom.getInflectionPoints().get(0).isRightLeftTurning());
    }

    @Test
    void visualisingTurningPoints() throws WrongInputSizeException {
        Polynom polynom = new Polynom(new double[] { 0.0, -1.0, 0.0, 1.0, 0.0 });
        System.out.println(polynom.getInflectionPoints());
    }

}
