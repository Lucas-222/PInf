public class Term implements Comparable {

    private final double coefficient;
    private final int exponent;

    public Term(double coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public int getExponent() {
        return exponent;
    }

    @Override
    public int compareTo(Object o) {
        if (this.getExponent() == ((Term) o).getExponent()) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Term{" +
                "coefficient=" + coefficient +
                ", exponent=" + exponent +
                '}';
    }
}
