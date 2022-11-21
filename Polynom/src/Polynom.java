import exceptions.InputToLongException;

import java.util.ArrayList;
import java.util.Arrays;

public class Polynom {
    private double[] koeffizienten;
    private boolean exceptionThrown = false;

    public Polynom(double[] koeffizienten) {
        this.koeffizienten = koeffizienten;
        try {
            checkException();
        } catch (InputToLongException e) {
            System.out.println(e.getMessage());
        }
    }

    private void checkException() throws InputToLongException {
        if (koeffizienten.length != 5) {
            throw new InputToLongException();
        }
    }








    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = koeffizienten.length-1; i >= 0; i--) {
            if (koeffizienten[i] != 0) {
                builder.append("[");
                builder.append(koeffizienten[i]);
                if (i != 0) {
                    builder.append("x");
                    if (i != 1) {
                        builder.append("^");
                        builder.append(i);
                    }
                }
                builder.append("]");
            }
        }
        return builder.toString();
    }

}
