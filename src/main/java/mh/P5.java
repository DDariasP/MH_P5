package mh;

import mh.tipos.*;
import mh.algoritmos.*;
import java.util.Random;
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
    public static final int[] MAXITER = {100, 100};
    public static final int[] RATIO = {5, 5};
    public static final String[] P = {"Rosenbrock", "Rastrigin"};
    public static final double[] MINX = {-1.5, -5.0};
    public static final double[] MAXX = {2.0, 5.0};
    public static final double[] MINY = {-0.5, -5.0};
    public static final double[] MAXY = {3.0, 5.0};
    public static double[] VMINX, VMAXX, VMINY, VMAXY;
    public static BusquedaLocal[][] BL;

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
            VMAXX[t] = (abs(MINX[t]) + abs(MAXX[t])) / MAXITER[t];
            VMINX[t] = -VMAXX[t];
            VMAXY[t] = (abs(MINY[t]) + abs(MAXY[t])) / MAXITER[t];
            VMINY[t] = -VMAXY[t];
        }

        //BUSQUEDA LOCAL
        BL = new BusquedaLocal[P.length][SEED.length];
        for (int t = 0; t < P.length; t++) {
            System.out.println("BL - " + P[t]);
            System.out.println("coste\t\t\teval\titer");
            for (int i = 0; i < SEED.length; i++) {
                BL[t][i] = new BusquedaLocal(i, t);
                BL[t][i].ejecutarBL();
            }
            System.out.println("------------------------------------------------------");
            //CONVERGENCIA
            Grafica convBL = new Grafica(BL[t], P[t]);
            convBL.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            convBL.setBounds(200, 350, 800, 400);
            convBL.setTitle("BL - " + P[t]);
            convBL.setVisible(true);
        }
        
        //PSO

        /*
        Random rand = new Random();
        Grafo gOPT = new Grafo();

        for (int j = 0; j < 10; j++) {

            Lista<Particula> n = new Lista<>();
            for (int i = 0; i < 10; i++) {
                int pos = rand.nextInt(10);
                n.add(new Particula(pos, pos, pos));
            }

            //CAMINO
            gOPT.Grafo(n);
            gOPT.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            gOPT.setBounds(200, 350, 800, 400);
            gOPT.setTitle(P[0] + ".tsp - Optima");
            gOPT.setVisible(true);

            Thread.sleep(500);

        }
         */
    }
}
