import exceptions.InputToLongException;

public class Polynom {
    public final String[] SYMMETRIES = new String[] {"Axisymmetric", "Pointsymmetric", "No symmetry"};
    private final double[] coefficients;
    private int degree;
    private String exception;
    private String symmetry;

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

    private void initialiseDegree() {
        try {
            checkException();
        } catch (InputToLongException e) {
            exception = e.getMessage();
        }

        boolean initialised = false;
        for (int i = coefficients.length-1; i >= 0; i--) {
            if (!initialised && coefficients[i] != 0) {
                degree = i;
                initialised = true;
            }
        }
    }

    public double functionValue(double x) {
        double functionValue = 0;

        for (int i = 0; i < coefficients.length; i++) {
            functionValue += coefficients[i] * Math.pow(x, i);
        }

        return functionValue;
    }

    public void initialiseSymmetry() {
        int totalNumbers = 0;
        int evenNumbers = 0;
        int oddNumbers = 0;

        for (int i = 0; i < coefficients.length; i++) {
            if (coefficients[i] != 0) {
                if (i % 2 == 0) evenNumbers++; else oddNumbers++;
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

    private void checkException() throws InputToLongException {
        if (coefficients.length != 5) throw new InputToLongException();
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
        return builder.toString();
    }

}
