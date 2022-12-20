import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class TestPolynomToString {

    @Test
    void test0() {
        ArrayList<Term> terms = new ArrayList<>();
        terms.add(new Term(3.0, 0));
        Polynom polynom = new Polynom(terms);
        assertEquals("f(x) = 3", polynom.toString());
    }

    @Test
    void test1() {
        ArrayList<Term> terms = new ArrayList<>();
        terms.add(new Term(3.0, 0));
        terms.add(new Term(4.0, 1));
        Polynom polynom = new Polynom(terms);
        assertEquals("f(x) = 4x + 3", polynom.toString());
    }

    @Test
    void test2() {
        ArrayList<Term> terms = new ArrayList<>();
        terms.add(new Term(3.0, 0));
        terms.add(new Term(4.0, 1));
        terms.add(new Term(6.0, 2));
        Polynom polynom = new Polynom(terms);
        assertEquals("f(x) = 6x^2 + 4x + 3", polynom.toString());
    }

    @Test
    void test10() {
        ArrayList<Term> terms = new ArrayList<>();
        terms.add(new Term(3.0, 0));
        terms.add(new Term(4.0, 1));
        terms.add(new Term(6.7, 2));
        terms.add(new Term(3.4, 3));
        terms.add(new Term(34.0, 4));
        terms.add(new Term(86.0, 5));
        terms.add(new Term(-3.9, 6));
        terms.add(new Term(4.0, 7));
        terms.add(new Term(16.0, 8));
        terms.add(new Term(3.0, 9));
        Polynom polynom = new Polynom(terms);
        assertEquals("f(x) = 3x^9 + 16x^8 + 4x^7 - 3.9x^6 + 86x^5 + 34x^4 + 3.4x^3 + 6.7x^2 + 4x + 3", polynom.toString());
    }

    @Test
    void testDegree() {
        ArrayList<Term> terms = new ArrayList<>();
        terms.add(new Term(1.0, 0));
        terms.add(new Term(2.0, 2));
        Polynom polynom = new Polynom(terms);
        assertEquals(2, polynom.getDegree());
    }

    @Test
    void testNoDegree() {
        Polynom polynom = new Polynom(new ArrayList<>());
        assertEquals(0, polynom.getDegree());
    }

    @Test
    void testPunktsymmetrie() {
        ArrayList<Term> terms = new ArrayList<>();
        terms.add(new Term(4.0, 3));
        terms.add(new Term(1.0, 1));
        Polynom polynom = new Polynom(terms);
        assertTrue(polynom.isPointsymmetric());
    }

    @Test
    void testAchsensymmetrie() {
        ArrayList<Term> terms = new ArrayList<>();
        terms.add(new Term(2.0, 2));
        terms.add(new Term(1.0, 0));
        Polynom polynom = new Polynom(terms);
        assertTrue(polynom.isAxissymmetric());
    }

    @Test
    void testNoSymmetrie() {
        ArrayList<Term> terms = new ArrayList<>();
        terms.add(new Term(2.0, 2));
        terms.add(new Term(4.0, 1));
        terms.add(new Term(1.0, 0));
        Polynom polynom = new Polynom(terms);
        assertFalse(polynom.isPointsymmetric());
        assertFalse(polynom.isAxissymmetric());
    }

    @Test
    void testNullSymmetrie() {
        Polynom polynom = new Polynom(new ArrayList<>());
        assertFalse(polynom.isPointsymmetric());
        assertFalse(polynom.isAxissymmetric());
    }

}
