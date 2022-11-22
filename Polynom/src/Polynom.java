import exceptions.WrongInputSizeException;

public class Polynom {
    public final String[] SYMMETRIES = new String[] {"axisymmetric", "pointsymmetric", "not symmetric"};
    private final double[] coefficients;
    private String exception;
    private String symmetry;
    private int degree;

    public Polynom(double[] coefficients) {
        this.coefficients = coefficients;
        initialiseDegree();
        initialiseSymmetry();
    }

    public int getDegree() {
        return degree;
    }

    public String getSymmetry() {
        return symmetry;
    }

    private void checkException() throws WrongInputSizeException {
        if (coefficients.length != 5) throw new WrongInputSizeException(coefficients.length);
    }

    private void initialiseDegree() {
        try {
            checkException();
        } catch (WrongInputSizeException e) {
            exception = e.getMessage();
            System.err.println(exception);
        }

        boolean initialised = false;
        for (int i = coefficients.length-1; i >= 0; i--) {
            if (!initialised && coefficients[i] != 0) {
                degree = i;
                initialised = true;
            }
        }
    }

    public void initialiseSymmetry() {
        int totalNumbers = 0;
        int evenNumbers = 0;
        int oddNumbers = 0;

        for (int i = 0; i < coefficients.length; i++) {
            if (coefficients[i] != 0) {
                if (i % 2 == 0) evenNumbers++;
                else oddNumbers++;
                totalNumbers++;
            }
        }

        if (totalNumbers == evenNumbers) {
            symmetry = SYMMETRIES[0];
        } else if (totalNumbers == oddNumbers) {
            symmetry = SYMMETRIES[1];
        } else {
            symmetry = SYMMETRIES[2];
        }

    }

    public double functionValue(double x) {
        if (exception == null) {
            double functionValue = 0.0;

            for (int i = 0; i < coefficients.length; i++) {
                functionValue += coefficients[i] * Math.pow(x, i);
            }

            return functionValue;
        } return -1;
    }

    @Override
    public String toString() {
        if (exception != null) return exception;

        double[] temp = coefficients;
        int firstValueindex = -1;
        for (int i = coefficients.length-1; i >= 0; i--) if (coefficients[i] != 0) firstValueindex = i;

        StringBuilder builder = new StringBuilder("f(x) = ");
        for (int i = temp.length-1; i >= 0; i--) {
            if (temp[i] != 0) {
                builder.append(temp[i]);
                if (i != 0) {
                    builder.append("x");
                    if (i != 1) builder.append("^").append(i);
                    if (i > firstValueindex){
                        if (i - 1 != 0 && temp[i - 1] < 0) {
                            builder.append(" - ");
                            temp[i - 1] *= -1;
                        } else {
                            builder.append(" + ");
                        }
                    }
                }
            }
        }

        System.out.println(builder);
        System.out.println("The symmetry is: " + symmetry);
        System.out.println("The degree is: " + degree + "\n");
        return builder.toString();
    }

}
