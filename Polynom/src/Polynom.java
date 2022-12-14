import exceptions.WrongInputSizeException;
import java.util.*;

public class Polynom {
    private final double[] coefficients;
    private int derivationCounter = 0;
    private TurningPoint minima;
    private TurningPoint maxima;

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

    public TurningPoint getMaxima() {
        getExtremePoints();
        return maxima;
    }

    public TurningPoint getMinima() {
        getExtremePoints();
        return minima;
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
            // Multiply the coefficient with the exponent and subtract 1 from the exponent
            derivation[i] = (i+1) * coefficients[i+1];
        }

        return derivation;
    }

    public Polynom derivationPolynom() {
        return new Polynom(derivationCoefficients(), (derivationCounter+1));
    }

    public ArrayList<Double> getNull() {
        // If function is linear or quadratic (degree 1 or 2), use the quadratic formula else return a new ArrayList
        return getDegree() == 1 ? getNullLinear() : getDegree() == 2 ? getNullQuadraticPQ() : new ArrayList<>();
    }

    private ArrayList<Double> getNullLinear() {
        // Multiply the value with the lowest exponent by -1 and divide it by the value with the exponent 1
        return new ArrayList<>(List.of((coefficients[0] * -1) / coefficients[1]));
    }

    private ArrayList<Double> getNullQuadraticPQ() {
        // divide p and q by the value with the exponent 2
        double p = coefficients[1] / coefficients[2];
        double q = coefficients[0] / coefficients[2];

        double sqrt = Math.sqrt(Math.pow((p / 2), 2) - q);
        double x1 = -(p / 2) + sqrt;
        double x2 = -(p / 2) - sqrt;

        return areNullsAValidNumber(x1, x2);
    }

    private ArrayList<Double> getNullQuadraticMidnight() {
        double a = coefficients[2];
        double b = coefficients[1];
        double c = coefficients[0];

        double sqrt = Math.sqrt(Math.pow(b, 2) - (4 * a * c));
        double x1 = ((b * -1) + sqrt) / (2 * a) ;
        double x2 = ((b * -1) - sqrt) / (2 * a) ;

        return areNullsAValidNumber(x1, x2);
    }

    private ArrayList<Double> areNullsAValidNumber(double x1, double x2) {
        ArrayList<Double> list = new ArrayList<>();
        // If x1 is not NaN
        if (!Double.isNaN(x1)) {
            list.add(x1);
        }
        // If x2 is not NaN and x2 is not x1
        if (!Double.isNaN(x2) && x1 != x2) {
            list.add(x2);
        }
        return list;
    }

    public Polynom divide(Polynom polynom) throws WrongInputSizeException {
        // Create temporary polynom for writing with the coefficients of the current polynom
        Polynom temp = new Polynom(this.coefficients);
        // Store the coefficients of the multiplication in qoutient
        double[] quotient = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };

        // Loop through every coefficient in the second polynom for every coefficient in the first polynom
        for (int i = this.getDegree(); i >= polynom.getDegree(); i--) {
            // Add the first coefficient divided by the last coefficient in the second polynom
            quotient[i - polynom.getDegree()] = temp.coefficients[i] / polynom.coefficients[polynom.getDegree()];
            // Loop through every coefficient in the second polynom
            for (int j = polynom.getDegree(); j >= 0; j--) {
                // Subtract the second polynom multiplied by the quotient from the first polynom
                temp.coefficients[i - polynom.getDegree() + j] -= quotient[i - polynom.getDegree()] * polynom.coefficients[j];
            }
        }

        return new Polynom(quotient);
    }

    public Polynom multiply(Polynom polynom) throws WrongInputSizeException {
        // Store the coefficients of the multiplication in product
        double[] product = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };

        // Loop through every coefficient in the second polynom for every coefficient in the first polynom
        for (int i = 0; i < this.coefficients.length; i++) {
            for (int j = 0; j < polynom.coefficients.length; j++) {
                // If the length is bigger than 4 --> continue
                if (i+j > 4) continue;
                // Add the first coefficient times the second coefficient
                product[i+j] += this.coefficients[i] * polynom.coefficients[j];
            }
        }

        // return a new Polynom with the product as the coefficients
        return new Polynom(product);
    }

    public Polynom add(Polynom polynom) throws WrongInputSizeException {
        // Return a new Polynom as the sum of two others
        return calculate(polynom, '+');
    }

    public Polynom substract(Polynom polynom) throws WrongInputSizeException {
        // Return a new Polynom as the difference of two others
        return calculate(polynom, '-');
    }

    private Polynom calculate(Polynom polynom, char operator) throws WrongInputSizeException {
        double[] result = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };

        // Loop through every coefficient in the second polynom for every coefficient in the first polynom
        for (int i = 0; i <= Math.max(this.getDegree(), polynom.getDegree()); i++) {
            result[i] = (i <= this.getDegree() ? coefficients[i] : 0) + (operator == '+' ? (i <= polynom.getDegree() ? polynom.coefficients[i] : 0) : - (i <= polynom.getDegree() ? polynom.coefficients[i] : 0));
        }

        // Return a new polynom with the result as coefficients
        return new Polynom(result);
    }

    public void getExtremePoints() {
        if (this.getDegree() == 2) {
            // If the degree is 2 than the turningpoint is the maxima and minima
            this.minima = getTurningPoints().get(0);
            this.maxima = getTurningPoints().get(0);
        } else if (this.getDegree() == 3) {
            ArrayList<TurningPoint> turningPoints = getTurningPoints();

            for (TurningPoint turningPoint : turningPoints) {
                // Get the function value from the second derivation at the turning point xValue
                double yValue = this.derivationPolynom().derivationPolynom().functionValue(turningPoint.getXValue());

                // If the yValue is bigger than 0 than it is a maxima else if it smaller it is a minima else it's not a turningpoint
                if (yValue > 0) {
                    this.minima = turningPoint;
                } else if (yValue < 0) {
                    this.maxima = turningPoint;
                }

            }

        }

    }

    private ArrayList<TurningPoint> getTurningPoints() {
        ArrayList<TurningPoint> turningPoints = new ArrayList<>();

        // Get the nulls of the first derivation
        for (double xValue : derivationPolynom().getNull()) {
            // Get the y value of the null
            double yValue = functionValue(xValue);
            // Add a new TurningPoint with the x and y value to the list
            turningPoints.add(new TurningPoint(xValue == -0 ? 0 : xValue, yValue == -0 ? 0 : yValue, true));
        }

        // just for testing
        for (TurningPoint turningPoint : turningPoints) {
            System.out.println(turningPoint);
        }

        return turningPoints;
    }

    private String getOperator(int i) {
        // Check if the value is negative
        String operator = coefficients[i] < 0 ? "-" : i >= getDegree() ? "" : "+";
        // If operator is not the first operator, add whitespaces around it
        return i < getDegree() ? " " + operator + " " : operator;
    }

    private String getNumber(int i) {
        // If number is 1 --> (1) not (1x),  If number is an integer --> (3x) not (3.0x), Default --> (4.56x)
        return coefficients[i] == 1 && i >= 1 ? "" : coefficients[i] == Math.round(coefficients[i]) ? String.valueOf((int) Math.abs(coefficients[i])) : String.valueOf(Math.abs(coefficients[i]));
    }

    private String getExponent(int i) {
        // If exponent is 0 --> (x^0) not (x), If exponent is 1 --> (x) not (x^1), Default --> (x^2)
        return i == 0 ? "" : i == 1 ? "x" : "x^" + i;
    }

    @Override
    public String toString() {
        // Create a StringBuilder initialized with f(x) = | for every derivation add one (')
        StringBuilder builder = new StringBuilder("f" + "'".repeat(derivationCounter) + "(x) = ");

        for (int i = coefficients.length-1; i >= 0; i--) {
            if (coefficients[i] == 0) continue;
            // Fill the builder with the operator, number and exponent
            builder.append(getOperator(i)).append(getNumber(i)).append(getExponent(i));
        }
        return builder.toString();
    }

}
