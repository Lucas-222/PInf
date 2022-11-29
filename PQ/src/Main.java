public class Main {

    public static void main(String[] args) {
        System.out.println(new PQ(3, -6, -9));
    }

    public static double average(double num1, double num2, double num3) {
        return (num1 + num2 + num3) / 3;
    }

    public static double average(double[] arr) {
        double sum = 0;
        for (double v : arr) {
            sum += v;
        }
        return sum / arr.length;
    }

}
