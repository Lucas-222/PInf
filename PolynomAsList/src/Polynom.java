import java.util.ArrayList;
import java.util.Collections;

public class Polynom {
    private ArrayList<Term> terms;
    private int derivationCounter = 0;

    public Polynom(ArrayList<Term> terms) {
        this.terms = terms;
        this.sort();
    }

    public Polynom(ArrayList<Term> terms, int derivationCounter) {
        this.terms = terms;
        this.derivationCounter = derivationCounter;
        this.sort();
    }

    public int getDegree() {
        return this.terms.get(terms.size()-1).getExponent();
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

    private void sort() {
        // Create temp list for writing
        ArrayList<Term> temp = new ArrayList<>();

        // Fill list with nulls
        for (int i = 0; i < terms.size()+1; i++) {
            temp.add(new Term(0.0, i));
        }

        for (int i = 0; i < temp.size(); i++) {
            for (Term term : terms) {
                // If the index is the same as the exponent
                if (term.getExponent() == i) {
                    // Add the current coefficient to the old
                    temp.set(i, new Term(temp.get(i).getCoefficient() + term.getCoefficient(), i));
                }
            }
        }

        // Set terms to the temp list
        terms = temp;
    }

    public double functionValue(double x) {
        double functionValue = 0.0;

        for (Term term : this.terms) {
            functionValue += term.getCoefficient() * Math.pow(x, term.getExponent());
        }
        return functionValue;
    }

    public ArrayList<Term> derivationCoefficients() {
        ArrayList<Term> derivation = new ArrayList<>();

        for (int i = 0; i < getDegree(); i++) {
            double coefficient = terms.get(i).getCoefficient() * terms.get(i).getExponent();
            int exponent = terms.get(i).getExponent() - 1;
            derivation.add(new Term(coefficient, exponent));
        }

        return derivation;
    }

    public Polynom derivationPolynom() {
        return new Polynom(this.derivationCoefficients(), (this.derivationCounter+1));
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
            // Operator
            stringBuilder.append(getOperator(term, first));
            // Number
            stringBuilder.append(getNumber(term));
            // Exponent
            stringBuilder.append(getExponent(term));
            // Set first to false
            first = false;
        }

        Collections.reverse(terms);
        return stringBuilder.toString();
    }

}
