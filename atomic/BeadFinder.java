import java.awt.Color;
import java.util.ArrayList;

public class BeadFinder {
    private ArrayList<Blob> list;

    //  finds all blobs in the specified picture using luminance threshold tau
    public BeadFinder(Picture picture, double tau) {
        list = new ArrayList<>();
        Picture pic = new Picture(picture);
        boolean[][] array = new boolean[pic.height()][pic.width()];
        for (int col = 0; col < pic.width(); col++) {
            for (int row = 0; row < pic.height(); row++) {
                Color color = pic.get(col, row);
                double lum = Luminance.intensity(color);
                if (lum > tau) {
                    array[row][col] = true;

                }
            }

        }

        int count = 0;

        boolean[][] newArray = new boolean[pic.height()][pic.width()];
        for (int col = 0; col < pic.width(); col++) {
            for (int row = 0; row < pic.height(); row++) {
                count = beadfinder(array, newArray, list, col, row, count);

            }

        }

    }

    public int beadfinder(boolean[][] arr, boolean[][] newArr, ArrayList<Blob> list, int col,
                          int row, int count) {
        int height = newArr.length;
        int column = newArr[0].length;
        if (col < 0 || col >= column) return count;
        if (row < 0 || row >= height) return count;
        if (!arr[row][col]) return count;
        if (newArr[row][col]) return count;
        // newArr[col][row] = true;
        list.add(count++, new Blob());
        dfs(arr, newArr, list.get(count - 1), col, row);
        return count;


    }

    public void dfs(boolean[][] arr, boolean[][] newArr, Blob blob, int col, int row) {
        int height = newArr.length;
        int column = newArr[0].length;
        if (col < 0 || col >= column) return;
        if (row < 0 || row >= height) return;
        if (!arr[row][col]) return;
        if (newArr[row][col]) return;
        blob.add(col, row);
        newArr[row][col] = true;
        dfs(arr, newArr, blob, col, row + 1);
        dfs(arr, newArr, blob, col + 1, row);
        dfs(arr, newArr, blob, col - 1, row);
        dfs(arr, newArr, blob, col, row - 1);

    }


    //  returns all beads (blobs with >= min pixels)
    public Blob[] getBeads(int min) {
        int n = list.size();
        int count = 0;
        for (int i = 0; i < n; i++) {
            // blobs[i] = list.get(i);
            Blob blob = list.get(i);
            if (blob.mass() >= min) {

                count++;
            }
        }
        Blob[] blobs = new Blob[count];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            // blobs[i] = list.get(i);
            Blob blob = list.get(i);
            if (blob.mass() >= min) {
                blobs[cnt++] = blob;

            }
        }

        return blobs;
    }

    //  test client, as described below
    public static void main(String[] args) {
        int min = Integer.parseInt(args[0]);
        double tau = Double.parseDouble(args[1]);
        Picture picture = new Picture(args[2]);
        Picture picture2 = new Picture(args[3]);
        BeadFinder beadFinder = new BeadFinder(picture, tau);
        BeadFinder beadFinder2 = new BeadFinder(picture2, tau);
        Blob[] blobs = beadFinder.getBeads(min);
        Blob[] blobs2 = beadFinder2.getBeads(min);

        // test code

        for (int i = 0; i < blobs2.length; i++) {
            double closest = Double.POSITIVE_INFINITY;
            for (int j = 0; j < blobs.length; j++) {
                double d = blobs2[i].distanceTo(blobs[j]);
                if (d <= min) {
                    if (d < closest) {
                        closest = d;

                    }
                }
            }
            if (closest < min) {
                StdOut.println(closest);

            }

        }


    }
}
