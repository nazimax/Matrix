/**
 * Created by kissi on 05/10/17.
 */
public class Main {

    public static void main(String[] args) {

        double[][] a = {
                {-1.0865, -1.2817, -1.5037, -1.6252, -1.0190},
                {-0.4939, -0.6130, -0.6399, -0.7223, -0.6794},
                {-1.0865, -0.9474, 0.2239, -0.1806, 0.0000},
                {1.4322, 1.5604, 1.5197, 1.8058, -1.0190},
                {1.2840, 1.3932, 0.5119, 0.7223, -0.3397},
                {0.3951, 0.0557, -1.3597, -1.0835, 0.6794},
                {-1.2347, -0.9474, 1.0878, 0.5417, -0.3397},
                {0.9877, 0.8916, -0.4959, -0.1806, 0.3397},
                {-0.1975, -0.1115, 0.6559, 0.7223, 2.3778}};

        Matrix m1 = new Matrix(a);
        //Matrix m2 = m1.transosedMatrix();
        //Matrix m3 = m2.productTwoMatrix(m1);

        //double[][] v = {{1, 2, 3, 4}, {5, 6, 7, 8}};
        //Matrix m6 = new Matrix(v);
        //double r = (double) 1 / 9;
        //Matrix m4=m3.productScalairMatrix(r);
        System.out.println(m1.correlationMatrix());

        //System.out.println(m4.format(4));
        //m2=m1.productTwoMetrix(m1);
        //System.out.println(m2.productTwoMetrix(m1));
        //System.out.println(m1.identity(10));
    }


}