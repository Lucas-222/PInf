import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFunctionValue {

    @Test
    void testFunctionValue0() {
        ArrayList<Term> terms = new ArrayList<>();
        terms.add(new Term(3.0, 0));
        Polynom polynom = new Polynom(terms);
        assertEquals(3.0, polynom.functionValue(1));
    }

    @Test
    void testFunctionValue1() {
        ArrayList<Term> terms = new ArrayList<>();
        terms.add(new Term(3.0, 0));
        terms.add(new Term(4.0, 1));
        Polynom polynom = new Polynom(terms);
        assertEquals(11.0, polynom.functionValue(2));
    }

    @Test
    void testFunctionValue2() {
        ArrayList<Term> terms = new ArrayList<>();
        terms.add(new Term(3.0, 0));
        terms.add(new Term(4.0, 1));
        terms.add(new Term(5.0, 2));
        Polynom polynom = new Polynom(terms);
        assertEquals(31.0, polynom.functionValue(2));
    }

    @Test
    void testFunctionValueMultiple() {
        ArrayList<Term> terms = new ArrayList<>();
        terms.add(new Term(3.0, 2));
        terms.add(new Term(1.4, 1));
        terms.add(new Term(-5.6, 0));
        terms.add(new Term(12.8, 4));
        terms.add(new Term(18.7, 5));
        Polynom polynom = new Polynom(terms);
        assertEquals(5606.499999999999, polynom.functionValue(3));
    }

}
