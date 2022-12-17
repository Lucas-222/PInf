import exceptions.WrongInputSizeException;

public abstract class PolynomialCalculations {

    public static Polynom divide(Polynom firstPolynom, Polynom secondPolynom) throws WrongInputSizeException {
        // Store the degrees of both polynoms in variables
        int aDegree = firstPolynom.getDegree();
        int bDegree = secondPolynom.getDegree();
        // Store the coefficients of both polynoms in variables
        double[] aCoefficients = firstPolynom.getCoefficients();
        double[] bCoefficients = secondPolynom.getCoefficients();
        // Store the coefficients of the multiplication in qoutient
        double[] quotient = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };

        // Loop through every coefficient in the second polynom for every coefficient in the first polynom
        for (int i = aDegree; i >= bDegree; i--) {
            // Add the first coefficient divided by the last coefficient in the second polynom
            quotient[i - bDegree] = aCoefficients[i] / bCoefficients[bDegree];
            // Loop through every coefficient in the second polynom
            for (int j = bDegree; j >= 0; j--) {
                // Subtract the second polynom multiplied by the quotient from the first polynom
                aCoefficients[i - bDegree + j] -= quotient[i - bDegree] * bCoefficients[j];
            }
        }

        return new Polynom(quotient);
    }

    public static Polynom multiply(Polynom firstPolynom, Polynom secondPolynom) throws WrongInputSizeException {
        // Store the coefficients of both polynoms in variables
        double[] aCoefficients = firstPolynom.getCoefficients();
        double[] bCoefficients = secondPolynom.getCoefficients();
        // Store the coefficients of the multiplication in product
        double[] product = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };

        // Loop through every coefficient in the second polynom for every coefficient in the first polynom
        for (int i = 0; i < aCoefficients.length; i++) {
            for (int j = 0; j < bCoefficients.length; j++) {
                // If the degree is smaller or equal 4, add the first coefficient times the second coefficient
                if (i+j <= 4) product[i+j] += aCoefficients[i] * bCoefficients[j];
            }
        }

        return new Polynom(product);
    }

    public static Polynom add(Polynom firstPolynom, Polynom secondPolynom) throws WrongInputSizeException {
        // Return a new Polynom as the sum of two others
        return calculate(firstPolynom, secondPolynom, '+');
    }

    public static Polynom substract(Polynom firstPolynom, Polynom secondPolynom) throws WrongInputSizeException {
        // Return a new Polynom as the difference of two others
        return calculate(firstPolynom, secondPolynom, '-');
    }

    private static Polynom calculate(Polynom firstPolynom, Polynom secondPolynom, char operator) throws WrongInputSizeException {
        // Store the degrees of both polynoms in variables
        int aDegree = firstPolynom.getDegree();
        int bDegree = secondPolynom.getDegree();
        // Store the coefficients of both polynoms in variables
        double[] aCoefficients = firstPolynom.getCoefficients();
        double[] bCoefficients = secondPolynom.getCoefficients();
        // Store the result in an array
        double[] result = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };

        // Loop through every coefficient in the second polynom for every coefficient in the first polynom
        for (int i = 0; i <= Math.max(aDegree, bDegree); i++) {
            // If operator is '+' add the coefficients else subtract them
            result[i] = (i <= aDegree ? aCoefficients[i] : 0) + (operator == '+' ? (i <= bDegree ? bCoefficients[i] : 0) : - (i <= bDegree ? bCoefficients[i] : 0));
        }

        return new Polynom(result);
    }

}
