import SpecialPoint.TurningPoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Polynom {
    private final ArrayList<Term> terms;
    private int derivationCounter = 0;

    public Polynom(ArrayList<Term> terms) {
        this.terms = terms;
        this.sortTermsByExponent();
    }

    public Polynom(ArrayList<Term> terms, int derivationCounter) {
        this.terms = terms;
        this.derivationCounter = derivationCounter;
        this.sortTermsByExponent();
    }

    public int getDegree() {
        int maxDegree = 0;
        for (Term term : terms) {
            if (term.getExponent() > maxDegree) {
                maxDegree = term.getExponent();
            }
        }
        return maxDegree;
    }

    private int getIndexByExponent(int exponent) {
        for (int i = 0; i < terms.size(); i++) {
            if (terms.get(i).getExponent() == exponent) {
                return i;
            }
        }
        return -1;
    }

    public boolean isAxissymmetric() {
        for (Term term : this.terms) {
            // If the exponent is odd, return false
            if (term.getCoefficient() != 0.0 && term.getExponent() % 2 != 0) {
                return false;
            }
        }
        // If every exponent where the value is not 0, is even
        return this.getDegree() != 0;
    }

    public boolean isPointsymmetric() {
        for (Term term : this.terms) {
            // If the exponent is even, return false
            if (term.getCoefficient() != 0 && term.getExponent() % 2 == 0) {
                return false;
            }
        }
        // If every exponent where the value is not 0, is odd
        return this.getDegree() != 0;
    }

    public void sortTermsByExponent() {
        if (this.terms.size() == 0) return;

        terms.sort(Comparator.comparingInt(Term::getExponent));

        for (int i = 0; i < terms.size() - 1; i++) {
            if (terms.get(i).getExponent() == terms.get(i + 1).getExponent()) {
                double newCoefficient = terms.get(i).getCoefficient() + terms.get(i + 1).getCoefficient();
                terms.set(i, new Term(newCoefficient, terms.get(i).getExponent()));
                terms.remove(i + 1);
            }
        }
    }

    public double functionValue(double x) {
        double functionValue = 0.0;

        for (Term term : this.terms) {
            functionValue += term.getCoefficient() * Math.pow(x, term.getExponent());
        }
        return functionValue;
    }

    public ArrayList<Term> derivationCoefficients() {
        ArrayList<Term> derivedTerms = new ArrayList<>();

        for (Term term : terms) {
            double coefficient = term.getCoefficient() * term.getExponent();
            int exponent = term.getExponent() - 1;
            derivedTerms.add(new Term(coefficient, exponent));
        }

        return derivedTerms;
    }

    public Polynom derivationPolynom() {
        return new Polynom(this.derivationCoefficients(), (this.derivationCounter+1));
    }

    public ArrayList<Double> getRoots() {
        return this.getDegree() == 1 ? this.getRootsLinear() : this.getDegree() == 2 ? this.getRootsQuadratic() : new ArrayList<>();
    }

    private ArrayList<Double> getRootsLinear() {
        // If the polynomial is a linear equation (degree 1),
        // the root is -b/a
        return new ArrayList<>(List.of(-terms.get(1).getCoefficient() / terms.get(0).getCoefficient()));
    }

    private ArrayList<Double> getRootsQuadratic() {
        // divide p and q by the value with the exponent 2
        double p = this.getIndexByExponent(1) == -1 ? 0 : this.terms.get(this.getIndexByExponent(1)).getCoefficient() / this.terms.get(this.getIndexByExponent(2)).getCoefficient();
        double q = this.getIndexByExponent(0) == -1 ? 0 : this.terms.get(this.getIndexByExponent(0)).getCoefficient() / this.terms.get(this.getIndexByExponent(2)).getCoefficient();

        double sqrt = Math.sqrt(Math.pow((p / 2), 2) - q);
        double x1 = -(p / 2) + sqrt;
        double x2 = -(p / 2) - sqrt;

        return this.areNullsAValidNumber(x1, x2);
    }

    private ArrayList<Double> areNullsAValidNumber(double x1, double x2) {
        // Method for checking if nulls are real numbers
        ArrayList<Double> list = new ArrayList<>();

        if (!Double.isNaN(x1)) {
            list.add(x1);
        }
        if (!Double.isNaN(x2) && x1 != x2) {
            list.add(x2);
        }
        return list;
    }

    public List<TurningPoint> getExtremePoints() {
        List<TurningPoint> extremePoints = new ArrayList<>();
        Polynom derivedPolynom = this.derivationPolynom();
        List<Double> roots = derivedPolynom.getRoots();
        for (double root : roots) {
            double yValue = this.functionValue(root);
            boolean isGlobal = (root > 0 && root < 1);
            extremePoints.add(new TurningPoint(root, yValue, isGlobal));
        }
        return extremePoints;
    }

    private String getOperator(Term term, boolean first) {
        return first ? term.getCoefficient() < 0 ? "-" : "" : term.getCoefficient() < 0 ? " - " : " + ";
    }

    private String getNumber(Term term) {
        double absolute = Math.abs(term.getCoefficient());
        return term.getCoefficient() == 1 ? "" : term.getCoefficient() == (int) term.getCoefficient() ? String.valueOf((int) absolute) : String.valueOf(absolute);
    }

    private String getExponent(Term term) {
        return term.getExponent() == 0 ? "" : term.getExponent() == 1 ? "x" : "x^" + term.getExponent();
    }

    @Override
    public String toString() {
        Collections.reverse(terms);
        StringBuilder stringBuilder = new StringBuilder("f" + "'".repeat(this.derivationCounter) + "(x) = ");
        boolean first = true;

        for (Term term : terms) {
            // If coefficient is 0
            if (term.getCoefficient() == 0.0) continue;
            // Fill the string builder with the operator, number and exponent
            stringBuilder.append(getOperator(term, first)).append(getNumber(term)).append(getExponent(term));
            // Set first to false
            first = false;
        }

        Collections.reverse(terms);
        return stringBuilder.toString();
    }

}
