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


        double[][] b = {
                {6,6,5,5.5,8},
                {8,8,8,8,9},
                {6,7,11,9.5,11},
                {14.5,14.5,15.5,15,8},
                {14,14,12,12,10},
                {11,10,5.5,7,13},
                {5.5,7,14,11.5,10},
                {13,12.5,8.5,9.5,12},
                {9,9.5,12.5,12,18},
        };

        double lamda1=2.8618, lamda2=1.15069, lamda3=0.983141,lamda4=0.00393846, lamda5= 0.000429702;
        double[]
                v1={-0.47639,-0.530244,-0.448126,-0.538074,-0.0394148},
                v2={-0.532636,-0.401597,0.569632,0.370555,0.305332},
                v3={0.154761,0.0936449,-0.227638,-0.109345,0.950528},
                v4={0.303163,-0.516953,-0.477369,0.641445,-0.0389643},
                v5={0.61114,-0.530613,0.441591,-0.386986,0.01401};




        Matrix m1 = new Matrix(b);
        Matrix m5=((m1.centredMatrix()).reduceCentredMatrix());

        System.out.println("======================REDUCED CENTRED MATRIX=========================\n\n "+m5);
        System.out.println("======================VAR CORRELATION MATRIX=========================\n\n"+((m1.centredMatrix()).reduceCentredMatrix()).varCorrelationOfReducedMatrix());
        Matrix m6 = m5.fatcor(v1,v2,v3,v4,v5);

        System.out.println("======================NEW COORD WITH EIGEN VECTORS =========================\n\n"+m6);
        Matrix m7=m6;
        //System.out.println("======================INDIVIDUAL CONTRIBUTION =========================\n\n"+m7.individualContribution(m7,lamda1,lamda2,lamda3));


        System.out.println("======================QUALITY OF REPRESENTATION =========================\n\n"+m6.qualityOfRepresentation(m6));

        System.out.println("======================FACTORIAL COORD OF VARIABLES =========================\n\n"+m6.coordFactorielOfVariable(m5,m6,lamda1,lamda2,lamda3));

    }
}