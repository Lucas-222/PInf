public class ExtraAufgabe {

    public static void main(String[] args) {
        evaluation();
    }

    public static void evaluation() {
        double sum = 0;
        double[] runtimes = calculateRuntimes(5, 1000000);
        System.out.println();
        for (int i = 0; i < runtimes.length; i++) {
            sum+=runtimes[i]/1000;
            System.out.println("Run [" + (i+1) + "]: " + (runtimes[i]/1000) + "s");
        }
        bubblesort(runtimes);
        System.out.println("\nMin: " + runtimes[0] + "s");
        System.out.println("Max: " + runtimes[runtimes.length-1] + "s");
        System.out.println("\nSum: " + sum + "s");
        System.out.println("Arithm: " + sum/runtimes.length+"s (Java)");
    }

    public static double[] calculateRuntimes(int runs, int length) {
        double[] runtimes = new double[runs];
        for (int i = 0; i < runs; i++) {
            long startTime = time();
            speedtest(length);
            long endTime = time();
            runtimes[i] = endTime-startTime;
        }
        return runtimes;
    }

    public static void speedtest(int length) {
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

    public static void bubblesort(double[] toSort) {
        double temp;
        for(int i = 1; i < toSort.length; i++) {
            for(int j = 0; j < toSort.length - i; j++) {
                if(toSort[j] > toSort[j+1]) {
                    temp = toSort[j];
                    toSort[j] = toSort[j+1];
                    toSort[j+1] = (int) temp;
                }
            }
        }
    }

    public static long time() {
        return System.currentTimeMillis();
    }

}
