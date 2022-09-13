import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Class {

    public static void main(String[] args) {
        test();
    }

    public static void helloWorld() {
        System.out.println("Hello World!");
    }

    public static void Input() throws IOException {
        System.out.print("Insert name: ");
        String name  = new BufferedReader(new InputStreamReader(System.in)).readLine();
        System.out.println("Hello "+name);
    }

    public static void matrix() {
        // Creation of the list
        ArrayList<String> list = new ArrayList<>();
        String string  = "WHATISTHEMATRIX";

        // Filling of the list
        for (int i = 0; i < string.length(); i+=3) {
            list.add(string.substring(i, i+3));
        }

        // Output of the list
        for (String s : list) {
            System.out.println(s);
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

    public static void rectangle() {
        Rectangle rectangle = new Rectangle(5, 10);
        System.out.println(rectangle.calcCircumference());
        System.out.println(rectangle.calcArea());
    }

    public static void test() {
        boolean b = true;
        int number = 100;
        ArrayList<Integer> numbers = new ArrayList<>();
        while (b) {
            numbers.add(number%16);
            number/=16;

            if (number == 0) {
                b = false;
            }
        }

        for (int i : numbers) {
            System.out.println(i);
        }


    }


}
