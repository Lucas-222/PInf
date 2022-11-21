import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestPolynom {

    @Test
    void testInput0() {
        Polynom polynom = new Polynom(new double[] {3, 0, 0, 0, 0});
        assertEquals("[3.0]", polynom.toString());
    }

    @Test
    void testInput1() {
        Polynom polynom = new Polynom(new double[] {3, 10, 0, 0, 0});
        assertEquals("[10.0x][3.0]", polynom.toString());
    }

    @Test
    void testInput2() {
        Polynom polynom = new Polynom(new double[] {4, 5, 2, 0, 0});
        assertEquals("[2.0x^2][5.0x][4.0]", polynom.toString());
    }

    @Test
    void testInput3() {
        Polynom polynom = new Polynom(new double[] {4, 5, 2, 7, 0});
        assertEquals("[7.0x^3][2.0x^2][5.0x][4.0]", polynom.toString());
    }

    @Test
    void testInput4() {
        Polynom polynom = new Polynom(new double[] {4, 5, 2, 8, 9});
        assertEquals("[9.0x^4][8.0x^3][2.0x^2][5.0x][4.0]", polynom.toString());
    }

}
