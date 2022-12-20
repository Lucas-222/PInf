import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDerivation {

    @Test
    void testDerivation0() {
        ArrayList<Term> terms = new ArrayList<>();
        terms.add(new Term(2.0, 0));
        Polynom polynom = new Polynom(terms);
        assertEquals("f'(x) = ", polynom.derivationPolynom().toString());
    }

    @Test
    void testDerivation1() {
        ArrayList<Term> terms = new ArrayList<>();
        terms.add(new Term(2.0, 0));
        terms.add(new Term(5.0, 1));
        Polynom polynom = new Polynom(terms);
        assertEquals("f'(x) = 5", polynom.derivationPolynom().toString());
    }

    @Test
    void testDerivation2() {
        ArrayList<Term> terms = new ArrayList<>();
        terms.add(new Term(2.0, 0));
        terms.add(new Term(3.0, 1));
        terms.add(new Term(5.0, 2));
        Polynom polynom = new Polynom(terms);
        assertEquals("f'(x) = 10x + 3", polynom.derivationPolynom().toString());
    }

    @Test
    void testDerivationMultiple() {
        ArrayList<Term> terms = new ArrayList<>();
        terms.add(new Term(2.0, 0));
        terms.add(new Term(3.0, 1));
        terms.add(new Term(5.0, 2));
        terms.add(new Term(6.0, 3));
        terms.add(new Term(1.0, 4));
        terms.add(new Term(8.0, 5));
        Polynom polynom = new Polynom(terms);
        assertEquals("f'(x) = 40x^4 + 4x^3 + 18x^2 + 10x + 3", polynom.derivationPolynom().toString());
    }

    @Test
    void testDerivationPolynomMinus() {
        ArrayList<Term> terms = new ArrayList<>();
        terms.add(new Term(-2.0, 0));
        terms.add(new Term(-5.0, 1));
        Polynom polynom = new Polynom(terms);
        assertEquals("f'(x) = -5", polynom.derivationPolynom().toString());
    }

    @Test
    void testMultipleDerivations() {
        ArrayList<Term> terms = new ArrayList<>();
        terms.add(new Term(4.0, 0));
        terms.add(new Term(5.0, 1));
        terms.add(new Term(9.0, 2));
        terms.add(new Term(3.0, 3));
        terms.add(new Term(2.0, 4));

        Polynom polynom = new Polynom(terms);
        assertEquals("f(x) = 2x^4 + 3x^3 + 9x^2 + 5x + 4", polynom.toString());
        Polynom firstDerivation = polynom.derivationPolynom();
        assertEquals("f'(x) = 8x^3 + 9x^2 + 18x + 5", firstDerivation.toString());
        Polynom secondDerivation = firstDerivation.derivationPolynom();
        assertEquals("f''(x) = 24x^2 + 18x + 18", secondDerivation.toString());
        Polynom thirdDerivation = secondDerivation.derivationPolynom();
        assertEquals("f'''(x) = 48x + 18", thirdDerivation.toString());
        Polynom fourthDerivation = thirdDerivation.derivationPolynom();
        assertEquals("f''''(x) = 48", fourthDerivation.toString());
        Polynom fifthDerivation = fourthDerivation.derivationPolynom();
        assertEquals("f'''''(x) = ", fifthDerivation.toString());
    }

}
