public class Main {

    public static void main(String[] args) {
        System.out.println(new PQ(12, -204, 630));
        //System.out.println(average(1, 2, 3));
        //System.out.println(average(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9}));
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
