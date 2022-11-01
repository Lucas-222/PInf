import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPQ {
    private final double NaN = Math.sqrt(-1);
    private final double pPositive = 4;
    private final double qPositive = 3;
    private final double pNegative = -4;
    private final double qNegative = -21;

    @Test
    void TestGetX1CorrectPositive() {
        PQ pq = new PQ(pPositive, qPositive);
        assertEquals(-1, pq.getX1());
    }

    @Test
    void TestGetX2CorrectPositive() {
        PQ pq = new PQ(pPositive, qPositive);
        assertEquals(-3, pq.getX2());
    }

    @Test
    void TestToStringCorrectPositive() {
        PQ pq = new PQ(pPositive, qPositive);
        assertEquals("x1: -1.0\nx2: -3.0", pq.toString());
    }

    @Test
    void TestGetX1CorrectNegative() {
        PQ pq = new PQ(pNegative, qNegative);
        assertEquals(7, pq.getX1());
    }

    @Test
    void TestGetX2CorrectNegative() {
        PQ pq = new PQ(pNegative, qNegative);
        assertEquals(-3, pq.getX2());
    }

    @Test
    void TestToStringCorrectNegative() {
        PQ pq = new PQ(pNegative, qNegative);
        assertEquals("x1: 7.0\nx2: -3.0", pq.toString());
    }

    @Test
    void TestGetX1False() {
        PQ pq = new PQ(NaN, NaN);
        assertEquals(NaN, pq.getX1());
    }

    @Test
    void TestGetX2False() {
        PQ pq = new PQ(NaN, NaN);
        assertEquals(NaN, pq.getX2());
    }

    @Test
    void TestToStringFalse() {
        PQ pq = new PQ(NaN, NaN);
        assertEquals("x1 and x2 are NaN", pq.toString());
    }

}