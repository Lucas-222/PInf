import jdk.jfr.Description;
import SpecialPoint.*;
import exceptions.*;
import java.util.*;

public class Polynom {
    private final double[] coefficients;
    private int derivationCounter = 0;
    private final ArrayList<TurningPoint> minima = new ArrayList<>();
    private final ArrayList<TurningPoint> maxima = new ArrayList<>();
    private final ArrayList<InflectionPoint> inflectionPoints = new ArrayList<>();

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

    public double[] getCoefficients() {
        return this.coefficients;
    }

    public ArrayList<InflectionPoint> getInflectionPoints() {
        if (this.inflectionPoints.size() == 0) this.setInflectionPoints();
        return this.inflectionPoints;
    }

    public ArrayList<TurningPoint> getMaxima() {
        if (this.maxima.size() == 0) this.setTurningPoints();
        return this.maxima;
    }

    public ArrayList<TurningPoint> getMinima() {
        if (this.minima.size() == 0) this.setTurningPoints();
        return this.minima;
    }

    public int getDegree() {
        // Loop through the array and return the first value which isn't 0
        for (int i = this.coefficients.length-1; i >= 0; i--) {
            if (this.coefficients[i] != 0) {
                return i;
            }
        }
        return 0;
    }

    public boolean isAxissymmetric() {
        for (int i = 0; i < this.coefficients.length; i++) {
            // If the exponent is odd, return false
            if (this.coefficients[i] != 0 && i % 2 != 0) {
                return false;
            }
        }
        // If every exponent where the value is not 0, is even
        return this.getDegree() != 0;
    }

    public boolean isPointsymmetric() {
        for (int i = 0; i < this.coefficients.length; i++) {
            // If the exponent is even, return false
            if (this.coefficients[i] != 0 && i % 2 == 0) {
                return false;
            }
        }
        // If every exponent where the value is not 0, is odd
        return this.getDegree() != 0;
    }

    public double functionValue(double x) {
        // Get the sum of all coefficients multiplied by x to the power of the exponent
        double functionValue = 0.0;

        for (int i = 0; i < this.coefficients.length; i++) {
            functionValue += this.coefficients[i] * Math.pow(x, i);
        }

        return functionValue;
    }

    public double[] derivationCoefficients() {
        // Example: (6x^4 - 12x^3 + 3x^2 + 4x + 8) --> (0 + 24x^3 - 36x^2 + 6x + 4)
        double[] derivation = { 0.0, 0.0, 0.0, 0.0, 0.0 };

        for (int i = 0; i < this.coefficients.length-1; i++) {
            // Multiply the coefficient with the exponent and subtract 1 from the exponent
            derivation[i] = (i+1) * this.coefficients[i+1];
        }

        return derivation;
    }

    public Polynom derivationPolynom() {
        return new Polynom(this.derivationCoefficients(), (this.derivationCounter+1));
    }

    public ArrayList<Double> getNull() {
        // If function is linear or quadratic (degree 1 or 2), use the quadratic formula else return a new ArrayList
        return this.getDegree() == 1 ? this.getNullLinear() : this.getDegree() == 2 ? this.getNullQuadratic() : new ArrayList<>();
    }

    private ArrayList<Double> getNullLinear() {
        // Multiply the value with the lowest exponent by -1 and divide it by the value with the exponent 1
        return new ArrayList<>(List.of((this.coefficients[0] * -1) / this.coefficients[1]));
    }

    private ArrayList<Double> getNullQuadratic() {
        // divide p and q by the value with the exponent 2
        double p = this.coefficients[1] / this.coefficients[2];
        double q = this.coefficients[0] / this.coefficients[2];

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

    @Description("Midnight is the same as the pq formula")
    /*private ArrayList<Double> getNullQuadraticMidnight() {
        double a = coefficients[2];
        double b = coefficients[1];
        double c = coefficients[0];

        double sqrt = Math.sqrt(Math.pow(b, 2) - (4 * a * c));
        double x1 = ((b * -1) + sqrt) / (2 * a) ;
        double x2 = ((b * -1) - sqrt) / (2 * a) ;

        return areNullsAValidNumber(x1, x2);
    }*/

    private void setTurningPoints() {
        if (this.getDegree() == 2) {
            this.setTurningPointsQuadratic();
        } else if (this.getDegree() == 3) {
            this.setTurningPointsCubic();
        }
    }

    private void setTurningPointsQuadratic() {
        // If the degree is 2 than the turningpoint is the maxima and minima
        TurningPoint turningPoint = this.calculateTurningPoints().get(0);
        this.minima.add(turningPoint);
        this.maxima.add(turningPoint);
    }

    private void setTurningPointsCubic() {
        // Loop through every turningpoint
        for (TurningPoint turningPoint : this.calculateTurningPoints()) {
            // Get the function value from the second derivation at the turning point xValue
            double yValue = this.derivationPolynom().derivationPolynom().functionValue(turningPoint.getXValue());
            // If the yValue is bigger than 0 than it is a maxima else if it smaller it is a minima
            if (yValue > 0) {
                this.minima.add(turningPoint);
            } else if (yValue < 0) {
                this.maxima.add(turningPoint);
            }
        }
    }

    private ArrayList<TurningPoint> calculateTurningPoints() {
        ArrayList<TurningPoint> turningPoints = new ArrayList<>();

        // Get the nulls of the first derivation
        for (double xValue : this.derivationPolynom().getNull()) {
            // Get the y value of the null
            double yValue = this.functionValue(xValue);
            // Add a new SpecialPoint.TurningPoint with the x and y value to the list
            turningPoints.add(new TurningPoint(xValue, yValue, true));
        }

        return turningPoints;
    }

    private void setInflectionPoints() {
        Polynom secondDerivation = this.derivationPolynom().derivationPolynom();

        for (double xValue : secondDerivation.getNull()) {
            // Check if third derivation is null
            if (secondDerivation.derivationPolynom().functionValue(xValue) == 0) return;

            // Is left-right or right-left
            boolean isRightLeftTurning = secondDerivation.functionValue(xValue + 0.1) > 0;

            // Get the y value
            double yValue = this.functionValue(xValue);

            // Add a new inflection point
            this.inflectionPoints.add(new InflectionPoint(xValue, yValue, isRightLeftTurning));
        }
    }

    private String getOperator(int i) {
        // Check if the value is negative
        String operator = this.coefficients[i] < 0 ? "-" : i >= this.getDegree() ? "" : "+";
        // If operator is not the first operator, add whitespaces around it
        return i < this.getDegree() ? " " + operator + " " : operator;
    }

    private String getNumber(int i) {
        // If number is 1 --> (1) not (1x),  If number is an integer --> (3x) not (3.0x), Default --> (4.56x)
        return this.coefficients[i] == 1 && i >= 1 ? "" : this.coefficients[i] == Math.round(this.coefficients[i]) ? String.valueOf((int) Math.abs(this.coefficients[i])) : String.valueOf(Math.abs(this.coefficients[i]));
    }

    private String getExponent(int i) {
        // If exponent is 0 --> (x^0) not (x), If exponent is 1 --> (x) not (x^1), Default --> (x^2)
        return i == 0 ? "" : i == 1 ? "x" : "x^" + i;
    }

    @Override
    public String toString() {
        // Create a StringBuilder initialized with f(x) = | for every derivation add one (')
        StringBuilder builder = new StringBuilder("f" + "'".repeat(this.derivationCounter) + "(x) = ");

        for (int i = this.coefficients.length-1; i >= 0; i--) {
            // If the coefficient is not 0, fill the builder with the operator, number and exponent
            if (this.coefficients[i] != 0) {
                builder.append(this.getOperator(i)).append(this.getNumber(i)).append(this.getExponent(i));
            }
        }

        return builder.toString();
    }

}
