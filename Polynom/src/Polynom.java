import exceptions.*;
import java.util.*;

public class Polynom {
    private final double[] coefficients;
    private int derivationCounter = 0;

    public Polynom(double[] coefficients) throws WrongInputSizeException {
        // Test if input is the wrong size
        if (coefficients.length != 5) {
            throw new WrongInputSizeException(coefficients.length);
        }
        this.coefficients = coefficients;
    }

    private Polynom(double[] coefficients, int derivationCounter) {
        // Private contructor, that's why no exception check needs to be performed
        this.coefficients = coefficients;
        this.derivationCounter = derivationCounter;
    }

    public int getDegree() {
        // Loop through the array and return the first value which isn't 0
        for (int i = coefficients.length-1; i >= 0; i--) {
            if (coefficients[i] != 0) {
                return i;
            }
        }
        return 0;
    }

    public boolean isAxissymmetric() {
        for (int i = 0; i < coefficients.length; i++) {
            // If the exponent is odd, return false
            if (coefficients[i] != 0 && i % 2 != 0) {
                return false;
            }
        }
        // If every exponent where the value is not 0, is even
        return getDegree() != 0;
    }

    public boolean isPointsymmetric() {
        for (int i = 0; i < coefficients.length; i++) {
            // If the exponent is even, return false
            if (coefficients[i] != 0 && i % 2 == 0) {
                return false;
            }
        }
        // If every exponent where the value is not 0, is odd
        return getDegree() != 0;
    }

    public double functionValue(double x) {
        // Get the sum of all coefficients multiplied by x to the power of the exponent
        double functionValue = 0.0;

        for (int i = 0; i < coefficients.length; i++) {
            functionValue += coefficients[i] * Math.pow(x, i);
        }

        return functionValue;
    }

    public double[] derivationCoefficients() {
        // Example: (6x^4 - 12x^3 + 3x^2 + 4x + 8) --> (0 + 24x^3 - 36x^2 + 6x + 4)
        double[] derivation = { 0.0, 0.0, 0.0, 0.0, 0.0 };

        for (int i = 0; i < coefficients.length-1; i++) {
            derivation[i] = (i+1) * coefficients[i+1];
        }

        return derivation;
    }

    public Polynom derivationPolynom() {
        return new Polynom(derivationCoefficients(), (derivationCounter+1));
    }

    public ArrayList<Double> getNull() {
        // If function is linear
        if (getDegree() == 1) {
            return getNullLinear();
        }
        // If function is quadratic
        if (getDegree() == 2) {
            if (getNullQuadraticPQ().toString().equals(getNullQuadraticMidnight().toString())) {
                return getNullQuadraticMidnight();
            }
            return new ArrayList<>(List.of(3.0, 1.0, 4.0));
        }
        return new ArrayList<>();
    }

    private ArrayList<Double> getNullLinear() {
        // Multiply the value with the lowest exponent by -1 and divide it by the value with the exponent 1
        return new ArrayList<>(List.of((coefficients[0] * -1) / coefficients[1]));
    }

    private ArrayList<Double> getNullQuadraticPQ() {
        ArrayList<Double> list = new ArrayList<>();
        // divide p and q by the highest coefficient to get the normal form
        double p = coefficients[1] / coefficients[2];
        double q = coefficients[0] / coefficients[2];

        double sqrt = Math.sqrt(Math.pow((p / 2), 2) - q);
        double x1 = -(p / 2) + sqrt;
        double x2 = -(p / 2) - sqrt;

        // If x1 is not NaN
        if (!Double.isNaN(x1)) {
            list.add(x1);
        }

        // If x2 is not NaN and x2 is not x1
        if (!Double.isNaN(x2) && list.get(0) != x2) {
            list.add(x2);
        }

        return list;
    }

    private ArrayList<Double> getNullQuadraticMidnight() {
        ArrayList<Double> list = new ArrayList<>();
        double a = coefficients[2];
        double b = coefficients[1];
        double c = coefficients[0];

        double sqrt = Math.sqrt(Math.pow(b, 2) - (4 * a * c));
        double x1 = ((b * -1) + sqrt) / (2 * a) ;
        double x2 = ((b * -1) - sqrt) / (2 * a) ;

        if (!Double.isNaN(x1)) {
            list.add(x1);
        }

        if (!Double.isNaN(x2) && x1 != x2) {
            list.add(x2);
        }

        return list;
    }

    public Polynom divide(Polynom polynom) throws WrongInputSizeException {
        int aDegree = getDegree();
        int bDegree = polynom.getDegree();

        double[] quotient = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };

        for (int i = aDegree; i >= bDegree; i--) {
            quotient[i - bDegree] = coefficients[i] / polynom.coefficients[polynom.getDegree()];
            for (int j = bDegree; j >= 0; j--) {
                coefficients[i - bDegree + j] -= quotient[i - bDegree] * polynom.coefficients[j];
            }
        }

        return new Polynom(quotient);
    }

    public Polynom add(Polynom polynom) throws WrongInputSizeException {
        int aDegree = getDegree();
        int bDegree = polynom.getDegree();

        int resultDegree = Math.max(aDegree, bDegree);
        double[] result = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };

        // Add the coefficients of the two polynomials
        for (int i = 0; i <= resultDegree; i++) {
            result[i] = (i <= aDegree ? coefficients[i] : 0) + (i <= bDegree ? polynom.coefficients[i] : 0);
        }

        return new Polynom(result);
    }

    public Polynom substract(Polynom polynom) throws WrongInputSizeException {
        int aDegree = getDegree();
        int bDegree = polynom.getDegree();

        int resultDegree = Math.max(aDegree, bDegree);
        double[] result = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };

        // Substract the coefficients of the two polynomials
        for (int i = 0; i <= resultDegree; i++) {
            result[i] = (i <= aDegree ? coefficients[i] : 0) - (i <= bDegree ? polynom.coefficients[i] : 0);
        }

        return new Polynom(result);
    }

    public Polynom multiply(Polynom polynom) throws WrongInputSizeException {
        double[] resultCoefficients = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };

        for (int i = 0; i < this.coefficients.length; i++) {
            for (int j = 0; j < polynom.coefficients.length; j++) {
                if (i+j > 4) continue;
                resultCoefficients[i+j] += this.coefficients[i] * polynom.coefficients[j];
            }
        }

        return new Polynom(resultCoefficients);
    }

    private String getOperator(int i) {
        // If number is negative
        if (coefficients[i] < 0) {
            return "-";
        }
        // If number is the last
        if (i >= getDegree()) {
            return "";
        }
        // If number is positive
        return "+";
    }

    private String getNumber(int i) {
        // If number is 1 --> (1) not (1x)
        if (coefficients[i] == 1 && i >= 1) {
            return "";
        }
        // If number is an integer --> (3x) not (3.0x)
        if (coefficients[i] % 2 == 0 || coefficients[i] % 2 == 1) {
            return String.valueOf((int) Math.abs(coefficients[i]));
        }
        // Default --> (4.56x)
        return String.valueOf(Math.abs(coefficients[i]));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("f" + "'".repeat(derivationCounter) + "(x) = ");

        for (int i = coefficients.length-1; i >= 0; i--) {
            if (coefficients[i] == 0) continue;

            // Get the right operator in front of the value
            if (i < getDegree()) {
                builder.append(" ").append(getOperator(i)).append(" ");
            } else {
                builder.append(getOperator(i));
            }

            builder.append(getNumber(i));

            // If exponent is 0 --> (3) not (3x^0)
            if (i == 0) continue;

            builder.append("x");

            // If exponent is 1 --> (3x) not (3x^1)
            if (i == 1) continue;

            // Default --> (3x^2)
            builder.append("^").append(i);

        }
        return builder.toString();
    }

}
