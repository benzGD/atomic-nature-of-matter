public class Blob {
    private int rx;
    private int ry;
    private int count;
    private double cx;
    private double cy;

    //  creates an empty blob
    public Blob() {

    }

    //  adds pixel (x, y) to this blob
    public void add(int x, int y) {
        rx = rx + x;
        ry = ry + y;
        count++;


    }

    //  number of pixels added to this blob
    public int mass() {
        return count;

    }

    public double comx() {
        cx = (double) rx / count;
        return cx;


    }

    public double comy() {
        cy = (double) ry / count;
        return cy;

    }

    //  Euclidean distance between the center of masses of the two blobs
    public double distanceTo(Blob that) {
        double x = Math.pow(this.comx() - that.comx(), 2);
        double y = Math.pow(this.comy() - that.comy(), 2);
        return Math.sqrt(x + y);
    }

    //  string representation of this blob (see below)
    public String toString() {
        return String.format("%2d (%8.4f, %8.4f)", count, comx(), comy());
    }

    //  tests this class by directly calling all instance methods
    public static void main(String[] args) {


    }
}
