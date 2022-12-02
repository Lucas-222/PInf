public class PQ {
    private final double p;
    private final double q;
    private double x1;
    private double x2;

    public PQ(double x, double p, double q) {
        this.p = p / x;
        this.q = q / x;
        calculate();
    }

    public PQ(double p, double q) {
        this.p = p;
        this.q = q;
        calculate();
    }

    private void calculate() {
        double pHalf = p / 2;
        double sqrt;

        // If q is negative add q else substract q
        if (q < 0) {
            // q needs to be a positive number that's why q*-1
            sqrt = Math.sqrt(Math.pow(pHalf, 2) + (q * -1));
        } else {
            sqrt = Math.sqrt(Math.pow(pHalf, 2) - q);
        }

        x1 = -pHalf + sqrt;
        x2 = -pHalf - sqrt;
    }

    public double getX1() {
        if (Double.isNaN(x1)) System.out.println("x1 is NaN");
        return x1;
    }

    public double getX2() {
        if (Double.isNaN(x2)) System.out.println("x2 is NaN");
        return x2;
    }

    @Override
    public String toString() {
        if (Double.isNaN(x1) || Double.isNaN(x2)) {
            return "x1 and x2 are NaN";
        }
        return "x1: " + x1 + "\nx2: " + x2;
    }
}
