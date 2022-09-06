public class ExtraAufgabe {

    public static void main(String[] args) {
        System.out.println(calculateArith()+"ms");
    }

    public static long calculateArith() {
        long arithMid = 0;
        long[] list = loop(5);
        for (long l : list) {
            arithMid+=l;
        }
        arithMid/=list.length;
        return arithMid;
    }

    public static long[] loop(int runs) {
        long[] arr = new long[runs];
        for (int i = 0; i < runs; i++) {
            long startTime = time();
            output();
            long endTime = time();
            arr[i] = endTime - startTime;
        }
        return arr;
    }

    public static void output() {
        for(int i = 0; i < 10000000; i++) {
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
