import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestPolynomSorted {

    @Test
    void testSorted() {
        ArrayList<Term> terms = new ArrayList<>();
        terms.add(new Term(3.0, 2));
        terms.add(new Term(4.0, 1));
        Polynom polynom = new Polynom(terms);
        assertEquals("f(x) = 3x^2 + 4x", polynom.toString());
    }

    @Test
    void testMultipleTermsWithTheSameExponent() {
        ArrayList<Term> terms = new ArrayList<>();
        terms.add(new Term(3.0, 2));
        terms.add(new Term(4.0, 2));
        Polynom polynom = new Polynom(terms);
        assertEquals("f(x) = 7x^2", polynom.toString());
    }

}
