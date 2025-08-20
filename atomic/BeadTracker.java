public class BeadTracker {
    public static void main(String[] args) {
        int min = Integer.parseInt(args[0]);
        double tau = Double.parseDouble(args[1]);
        double delta = Double.parseDouble(args[2]);
        for (int k = 3; k < args.length - 1; k++) {
            Picture picture = new Picture(args[k]);
            Picture picture2 = new Picture(args[k + 1]);
            BeadFinder beadFinder = new BeadFinder(picture, tau);
            BeadFinder beadFinder2 = new BeadFinder(picture2, tau);
            Blob[] blobs = beadFinder.getBeads(min);
            Blob[] blobs2 = beadFinder2.getBeads(min);
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
                if (closest <= delta) {
                    StdOut.println(String.format("%6.4f", closest));
                }

            }

        }

    }
}
