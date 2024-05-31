package mh;

import mh.algoritmos.*;
import static javax.swing.WindowConstants.*;
import static java.lang.Math.*;

/**
 *
 * @author diego
 */
public class P5 {

    public static final int[] SEED = {111, 222, 333, 123, 321};
    public static final int NUMP = 10;
    public static final int VECIN = 2;
    public static final int T = 100;
    public static final double OMEGA = 0.729;
    public static final double PHI1 = 1.49445;
    public static final double PHI2 = 1.49445;
    public static final double[] MAXITER = {30.0, 30.0};
    public static final int[] RATIO = {1, 1};
    public static final double[] V = {2.0, 2.0};
    public static final String[] P = {"Rosenbrock", "Rastrigin"};
    public static final double[] MINX = {-1.5, -5.0};
    public static final double[] MAXX = {2.0, 5.0};
    public static final double[] MINY = {-0.5, -5.0};
    public static final double[] MAXY = {3.0, 5.0};
    public static double[] VMINX, VMAXX, VMINY, VMAXY;
    public static BusquedaLocal[][] BL;
    public static PSO[][] PSOLocal, PSOGlobal;

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        //VELOCIDAD MAX/MIN
        VMINX = new double[P.length];
        VMAXX = new double[P.length];
        VMINY = new double[P.length];
        VMAXY = new double[P.length];
        for (int t = 0; t < P.length; t++) {
            VMAXX[t] = (abs(MINX[t]) + abs(MAXX[t])) / (MAXITER[t] * V[t]);
            VMINX[t] = -VMAXX[t];
            VMAXY[t] = (abs(MINY[t]) + abs(MAXY[t])) / (MAXITER[t] * V[t]);
            VMINY[t] = -VMAXY[t];
        }

        //BUSQUEDA LOCAL
        BL = new BusquedaLocal[P.length][SEED.length];
        for (int t = 0; t < P.length; t++) {
            System.out.println("BL - " + P[t]);
            System.out.println("coste\t\t\teval\titer");
            for (int i = 0; i < SEED.length; i++) {
                BL[t][i] = new BusquedaLocal(SEED[i], t);
                BL[t][i].ejecutarBL();
            }
            System.out.println("------------------------------------------------------");
            //CONVERGENCIA
            Grafica convBL = new Grafica(BL[t], t);
            convBL.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            convBL.setBounds(1000, 100, 800, 400);
            convBL.setTitle("BL - " + P[t]);
            convBL.setVisible(true);
        }

        //PSOLocal
        PSOLocal = new PSO[P.length][SEED.length];
        for (int t = 0; t < P.length; t++) {
            System.out.println("PSOLocal - " + P[t]);
            System.out.println("coste\t\t\teval\titer");
            for (int i = 0; i < SEED.length; i++) {
                PSOLocal[t][i] = new PSO(SEED[i], t, 'L');
                PSOLocal[t][i].ejecutarPSO();
            }
            System.out.println("------------------------------------------------------");
        }

        //PSOGlobal
        PSOGlobal = new PSO[P.length][SEED.length];
        for (int t = 0; t < P.length; t++) {
            System.out.println("PSOGlobal - " + P[t]);
            System.out.println("coste\t\t\teval\titer");
            for (int i = 0; i < SEED.length; i++) {
                PSOGlobal[t][i] = new PSO(SEED[i], t, 'G');
                PSOGlobal[t][i].ejecutarPSO();
            }
            System.out.println("------------------------------------------------------");
        }

        //GUARDAR
        Parser.escribir("R.txt");

    }
}
