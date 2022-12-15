import exceptions.WrongInputSizeException;

public abstract class PolynomialCalculations {

    public static Polynom divide(Polynom firstPolynom, Polynom secondPolynom) throws WrongInputSizeException {
        // Create temporary polynom for writing with the coefficients of the current polynom
        Polynom temp = new Polynom(firstPolynom.getCoefficients());
        // Store the coefficients of the multiplication in qoutient
        double[] quotient = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };

        // Loop through every coefficient in the second polynom for every coefficient in the first polynom
        for (int i = firstPolynom.getDegree(); i >= secondPolynom.getDegree(); i--) {
            // Add the first coefficient divided by the last coefficient in the second polynom
            quotient[i - secondPolynom.getDegree()] = temp.getCoefficients()[i] / secondPolynom.getCoefficients()[secondPolynom.getDegree()];
            // Loop through every coefficient in the second polynom
            for (int j = secondPolynom.getDegree(); j >= 0; j--) {
                // Subtract the second polynom multiplied by the quotient from the first polynom
                temp.getCoefficients()[i - secondPolynom.getDegree() + j] -= quotient[i - secondPolynom.getDegree()] * secondPolynom.getCoefficients()[j];
            }
        }

        return new Polynom(quotient);
    }

    public static Polynom multiply(Polynom firstPolynom, Polynom secondPolynom) throws WrongInputSizeException {
        // Store the coefficients of the multiplication in product
        double[] product = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };

        // Loop through every coefficient in the second polynom for every coefficient in the first polynom
        for (int i = 0; i < firstPolynom.getCoefficients().length; i++) {
            for (int j = 0; j < secondPolynom.getCoefficients().length; j++) {
                // If the length is bigger than 4 --> continue
                if (i+j > 4) continue;
                // Add the first coefficient times the second coefficient
                product[i+j] += firstPolynom.getCoefficients()[i] * secondPolynom.getCoefficients()[j];
            }
        }

        // return a new Polynom with the product as the coefficients
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
        double[] result = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };

        // Loop through every coefficient in the second polynom for every coefficient in the first polynom
        for (int i = 0; i <= Math.max(firstPolynom.getDegree(), secondPolynom.getDegree()); i++) {
            // If operator is '+' add the coefficients else subtract them
            result[i] = (i <= firstPolynom.getDegree() ? firstPolynom.getCoefficients()[i] : 0) + (operator == '+' ? (i <= secondPolynom.getDegree() ? secondPolynom.getCoefficients()[i] : 0) : - (i <= secondPolynom.getDegree() ? secondPolynom.getCoefficients()[i] : 0));
        }

        // Return a new polynom with the result as coefficients
        return new Polynom(result);
    }


}
