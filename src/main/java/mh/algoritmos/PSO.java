package mh.algoritmos;

import mh.*;
import mh.tipos.*;
import java.util.Random;
import static javax.swing.WindowConstants.*;

/**
 *
 * @author diego
 */
public class PSO {

    public final int SEED, t;
    public final char tipo;
    public Random rand;
    public Lista<Particula> swarm;
    public Lista<Particula> gBest;

    public PSO(int a, int b, char c) {
        SEED = a;
        t = b;
        tipo = c;
        rand = new Random(SEED);
        swarm = new Lista<>();
        gBest = new Lista<>();
    }

    public void ejecutarPSO() throws InterruptedException {
        PSO();
        Particula s = gBest.get(0);
        System.out.println(s.coste + "\t\t" + s.eval + "\t" + s.iter);
    }

    public void PSO() throws InterruptedException {
        int eval = 0;
        int iter = 0;
        Nube g = new Nube();

        for (int i = 0; i < P5.NUMP; i++) {
            Particula inicial = Particula.genRandom(rand, t);
            if (t == 0) {
                inicial.rosenbrock();
            } else {
                inicial.rastrigin();
            }
            inicial.pBestz = inicial.z;
            eval++;
            inicial.eval = eval;
            inicial.iter = iter;
            swarm.add(inicial);
        }
        Particula.vecindario(swarm);

        Particula.sort(swarm);
        gBest.add(0, swarm.get(0));
        while (iter < P5.MAXITER[t]) {
            iter++;
            for (int i = 0; i < P5.NUMP; i++) {
                Particula actual = swarm.get(i);
                actual.mover(gBest, rand, t, tipo);
                if (t == 0) {
                    actual.rosenbrock();
                } else {
                    actual.rastrigin();
                }
                eval++;
                actual.eval = eval;
                actual.iter = iter;
                if (actual.pBestz > actual.z) {
                    actual.pBestx = actual.x;
                    actual.pBesty = actual.y;
                    actual.pBestz = actual.z;
                }
            }

            g.Nube(swarm, t);
            g.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            g.setBounds(200, 200, 600, 600);
            g.setTitle("PSO/" + tipo + " - " + P5.P[t] + "/S" + SEED);
            g.setVisible(true);
            Thread.sleep(0);

            Particula.sort(swarm);
            Particula candidata = swarm.get(0);
            if (gBest.get(0).z > candidata.z) {
                gBest.add(0, candidata);
            }
        }

    }

}
