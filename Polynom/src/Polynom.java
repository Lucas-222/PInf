import exceptions.WrongInputSizeException;

public class Polynom {
    private final double[] coefficients;

    public Polynom(double[] coefficients) throws WrongInputSizeException {
        if (coefficients.length != 5) throw new WrongInputSizeException(coefficients.length);
        this.coefficients = coefficients;
    }

    public int getDegree() {
        for (int i = coefficients.length-1; i >= 0; i--) {
            if (coefficients[i] != 0) {
                return i;
            }
        }
        return 0;
    }

    public boolean isAxissymmetric() {
        for (int i = 0; i < coefficients.length; i++) {
            if (coefficients[i] != 0 && i % 2 != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isPointsymmetric() {
        for (int i = 0; i < coefficients.length; i++) {
            if (coefficients[i] != 0 && i % 2 == 0) {
                return false;
            }
        }
        return true;
    }

    public double functionValue(double x) {
        double functionValue = 0.0;

        for (int i = 0; i < coefficients.length; i++) {
            functionValue += coefficients[i] * Math.pow(x, i);
        }

        return functionValue;
    }

    public double[] derivationCoefficients() {
        double[] derivation = { 0.0, 0.0, 0.0, 0.0, 0.0 };

        for (int i = 0; i < coefficients.length-1; i++) {
            derivation[i] = (i+1) * coefficients[i+1];
        }

        return derivation;
    }

    public Polynom derivationPolynom() throws WrongInputSizeException {
        return new Polynom(derivationCoefficients());
    }

    private String getRightOperator(int i) {
        if (coefficients[i] < 0) {
            return "-";
        }
        if (i < getDegree()) {
            return "+";
        }
        return "";
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("f(x) = ");

        for (int i = coefficients.length-1; i >= 0; i--) {
            if (coefficients[i] != 0) {
                // Get the right operator in front of the value
                if (i < getDegree()) {
                    builder.append(" ").append(getRightOperator(i)).append(" ");
                } else {
                    builder.append(getRightOperator(i));
                }

                builder.append(Math.abs(coefficients[i]));

                // If exponent is 0 --> (3) not (3x^0)
                if (i == 0) continue;
                builder.append("x");
                // If exponent is 1 --> (3x) not (3x^1)
                if (i == 1) continue;
                // Default --> (3x^2)
                builder.append("^").append(i);
            }
        }
        return builder.toString();
    }

}
