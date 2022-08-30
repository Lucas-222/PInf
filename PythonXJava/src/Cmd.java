import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Cmd {

    public static void main(String[] args) throws IOException {
        System.out.println(faculty(5));
    }

    public static void oneA() {
        System.out.println("Hello World!");
    }

    public static void oneB() throws IOException {
        System.out.print("Insert name: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name  = reader.readLine();
        System.out.println("Hello " + name);
    }

    public static void oneC() {
        ArrayList<String> list = new ArrayList<>();
        String string  = "WHATISTHEMATRIX";

        for (int i = 0; i < string.length(); i+=3) {
            list.add(string.substring(i, i+3));
        }

        for (String s : list) {
            System.out.println(s);
        }

    }

    public static double maximum(double a, double b) {
        return Math.max(a, b);
    }

    public static int faculty(int number) {
        int faculty = 1;
        for (int i = 1; i <= number; i++) {
            faculty *= i;
        }
        return faculty;
    }

    public static void testRectangle() {
        Rectangle rectangle = new Rectangle(5, 10);
        System.out.println("Circumference: ");
        System.out.print(rectangle.calcCircumference());
        System.out.println("\nArea: ");
        System.out.print(rectangle.calcArea());
    }

}
