package mh.algoritmos;

import mh.*;
import mh.tipos.*;
import java.util.Random;

/**
 *
 * @author diego
 */
public class BusquedaLocal {

    public final int SEED, t;
    public Random rand;
    public Lista<Double> convergencia;
    public Particula s;

    public BusquedaLocal(int a, int b) {
        SEED = a;
        t = b;
        rand = new Random(SEED);
        convergencia = new Lista<>();
    }

    public void ejecutarBL() {
        BL();
        System.out.println(s.coste + "\t" + s.eval + "\t" + s.iter);
    }

    public void BL() {
        int eval = 0;
        int iter = 0;

        Particula inicial = Particula.genRandom(rand, t);
        if (t == 0) {
            inicial.rosenbrock();
        } else {
            inicial.rastrigin();
        }
        eval++;
        inicial.eval = eval;
        inicial.iter = iter;
        convergencia.add(inicial.z);

        s = inicial;
        Particula actual = inicial;
        Particula siguiente;
        while (iter < P5.MAXITER[t]) {

            iter++;
            for (int i = 0; i < P5.NUMP; i++) {
                siguiente = Particula.genVecino(actual, rand, t);
                if (t == 0) {
                    siguiente.rosenbrock();
                } else {
                    siguiente.rastrigin();
                }
                eval++;
                siguiente.eval = eval;
                siguiente.iter = iter;
                actual.vecinos.add(siguiente);
            }

            Particula.sort(actual.vecinos);
            siguiente = actual.vecinos.get(0);

            if (actual.z > siguiente.z) {
                actual = siguiente;
            }
            if (s.z > siguiente.z) {
                s = siguiente;
            }
            if (iter % P5.RATIO[t] == 0) {
                convergencia.add(s.z);
            }
        }
    }

}
