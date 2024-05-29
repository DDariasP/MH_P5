package mh.algoritmos;

import mh.P5;
import mh.tipos.*;
import java.util.Random;

/**
 *
 * @author diego
 */
public class SistemaHormigas {

    public final int SEED;
    public Random rand;
    public final double TAU0;
    public MDouble TAU;
    public Hormiga[] ant;
    public Hormiga elite;
    public int eval;
    public int iter;
    public Lista convergencia;

    public SistemaHormigas(int s, int t) {
        SEED = s;
        rand = new Random(SEED);
        TAU0 = 1.0 / (P5.CIU * P5.solG[t].mejor.coste);
        TAU = new MDouble(P5.CIU, P5.CIU, TAU0);
        ant = new Hormiga[P5.NUMH];
        elite = Hormiga.NULA;
        eval = 0;
        iter = 0;
        convergencia = new Lista<Integer>();
        SH(t);
    }

    public final void SH(int t) {

        while (true) {
            //NODO INICIAL
            for (int i = 0; i < P5.NUMH; i++) {
                int pos = rand.nextInt(P5.CIU);
                Nodo inicial = P5.listaCiu.get(pos);
                ant[i] = new Hormiga(i, inicial, P5.listaCiu);
            }

            //CONSTRUIR SOLUCIONES
            for (int i = 0; i < P5.NUMH; i++) {
                for (int j = 1; j < P5.CIU; j++) {
                    ant[i].transicion(TAU, rand);
                }
                ant[i].coste = P5.distancias.costeCamino(ant[i].cerrados);
                eval++;
                ant[i].eval = eval;
            }

            //MEJOR ACTUAL
            Hormiga actual = Hormiga.mejor(ant);

            //ACTUALIZAR FEROMONA
            TAU = Hormiga.actualizacion(ant, TAU, iter);

            //MEJOR GLOBAL
            if (elite.coste > actual.coste) {
                elite = actual;
            }

            //CONVERGENCIA
            if (iter % P5.RATIO[t] == 0) {
                convergencia.add(elite.coste);
                System.out.println("iter=" + iter);
            }

            //ITERACIONES
            iter++;
            if (iter > P5.MAXITER[t]) {
                break;
            }
        }
    }

}
