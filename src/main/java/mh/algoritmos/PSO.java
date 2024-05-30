package mh.algoritmos;

import mh.tipos.*;
import java.util.Random;

/**
 *
 * @author diego
 */
public class PSO {

    public final int SEED, t;
    public Random rand;
    public Lista<Double> convergencia;
    public Particula solucion;

    public PSO(int a, int b) {
        SEED = a;
        t = b;
        rand = new Random(SEED);
        convergencia = new Lista<>();
    }

    public void ejecutarPSO_B() {
        PSO_B();
        System.out.println(solucion.z + "\t" + solucion.eval + "\t" + solucion.iter);
    }

    //SE DESLIZA POR EL BORDE
    public void PSO_B() {

    }

    public void ejecutarPSO_C() {
        PSO_C();
        System.out.println(solucion.z + "\t" + solucion.eval + "\t" + solucion.iter);
    }

    //SALE POR EL OTRO LADO
    public void PSO_C() {

    }
}
