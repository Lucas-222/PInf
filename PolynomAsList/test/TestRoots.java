import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class TestRoots {
    @Test
    public void testGetRoots_degree1() {
        // Test the getRoots method with a linear equation (degree 1)
        ArrayList<Term> terms1 = new ArrayList<>(List.of(new Term(-4, 1), new Term(3, 0)));
        Polynom polynom1 = new Polynom(terms1);
        ArrayList<Double> expectedRoots1 = new ArrayList<>(List.of(1.3333333333333333));
        ArrayList<Double> actualRoots1 = polynom1.getRoots();
        Assert.assertEquals(expectedRoots1, actualRoots1);
    }

    @Test
    public void testGetRoots_degree2() {
        // Test the getRoots method with a quadratic equation (degree 2)
        ArrayList<Term> terms2 = new ArrayList<>(List.of(new Term(1, 2), new Term(-3, 1)));
        Polynom polynom2 = new Polynom(terms2);
        ArrayList<Double> expectedRoots2 = new ArrayList<>(List.of(3.0, 0.0));
        ArrayList<Double> actualRoots2 = polynom2.getRoots();
        Assert.assertEquals(expectedRoots2, actualRoots2);
    }

}