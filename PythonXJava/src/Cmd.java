import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Cmd {

    public static void main(String[] args) throws IOException {
        // 1
        System.out.println("(1)");
        System.out.print("\ta): ");
        oneA();
        System.out.print("\tb): ");
        oneB();
        System.out.println("\tc): ");
        oneC();
        // 2
        System.out.println("(2)");
        System.out.print("\tMaximum [10, 5]: ");
        System.out.print(maximum(10, 5));
        System.out.print("\n\tFaculty [5]:     ");
        System.out.print(faculty(5));
        // 3
        testRectangle();
    }

    public static void oneA() {
        System.out.println("Hello World!");
    }

    public static void oneB() throws IOException {
        System.out.print("Insert name: ");
        String name  = new BufferedReader(new InputStreamReader(System.in)).readLine();
        System.out.println("\t    Hello "+name);
    }

    public static void oneC() {
        ArrayList<String> list = new ArrayList<>();
        String string  = "WHATISTHEMATRIX";

        for (int i = 0; i < string.length(); i+=3) {
            list.add(string.substring(i, i+3));
        }

        for (String s : list) {
            System.out.println("\t    "+s);
        }

    }

    public static double maximum(double a, double b) {
        return Math.max(a, b);
    }

    public static int faculty(int a) {
        if (a == 1) {
            return a;
        } else {
            return a * faculty(a-1);
        }
    }

    public static void testRectangle() {
        Rectangle rectangle = new Rectangle(5, 10);
        System.out.println("\n(3)");
        System.out.println("\tRectangle [5, 10]:");
        System.out.print("\tCircumference: ");
        System.out.print(rectangle.calcCircumference());
        System.out.print("\n\tArea: ");
        System.out.print(rectangle.calcArea());
    }

}
