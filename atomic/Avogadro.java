public class Avogadro {
    public static void main(String[] args) {
        int count = 0;
        double radial_displacements = 0.0;
        double mean_sqr_displacement = 0.0;
        double meters_per_pixel = 0.175 * Math.pow(10, -6);
        while (!StdIn.isEmpty()) {
            radial_displacements = radial_displacements + Math.pow(
                    StdIn.readDouble() * meters_per_pixel, 2);
            count++;

        }


        mean_sqr_displacement = radial_displacements / (2 * count);
        double D = mean_sqr_displacement / 1.0;


        double eta = 9.135e-4;    // viscosity of water
        double a = 0.5e-6;      // bead radius
        double T = 297.0;       // temperature in Kelvin

        double k = (D * 6 * Math.PI * eta * a) / T;


        double avagadro = 8.31446 / k;  // avagadro

        StdOut.println(String.format("Boltzmann = %.4e", k));
        StdOut.println(String.format("Avogadro = %.4e", avagadro));


    }
}
