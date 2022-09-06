public class ExtraAufgabe {

    public static void main(String[] args) {
        System.out.println(calculateArith()+"s (Java)");
    }

    public static double calculateArith() {
        double arithMid = 0;
        long[] list = loop(1, 10000000);
        for (long l : list) {
            arithMid+=l;
        }
        return arithMid/list.length/1000;
    }

    public static long[] loop(int runs, int length) {
        long[] arr = new long[runs];
        for (int i = 0; i < runs; i++) {
            long startTime = time();
            output(length);
            long endTime = time();
            arr[i] = endTime - startTime;
        }
        return arr;
    }

    public static void output(int length) {
        for(int i = 0; i < length; i++) {
            System.out.println(i);
        }
    }

    public static void aufgabe1() {
        int counter = 100000000;

        for (int i = 0; i < 25; i++) {
            counter += 100000000;
            System.out.println(counter);
        }
    }

    public static long time() {
        return System.currentTimeMillis();
    }

}
