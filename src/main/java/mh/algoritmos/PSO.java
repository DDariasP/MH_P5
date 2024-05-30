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

    public static final String[] nombre = {"Borde", "Continua"};
    public final int SEED, t, tipo;
    public Random rand;
    public Lista<Particula> swarm;
    public Particula solucion;

    public PSO(int a, int b, int c) {
        SEED = a;
        t = b;
        tipo = c;
        rand = new Random(SEED);
        swarm = new Lista<>();
    }

    public void ejecutarPSO() throws InterruptedException {
        PSO();
        System.out.println(solucion.z + "\t" + solucion.eval + "\t" + solucion.iter);
    }

    public void PSO() throws InterruptedException {
        int eval = 0;
        int iter = 0;
        Grafo g = new Grafo();

        for (int i = 0; i < P5.NUMP; i++) {
            Particula inicial = Particula.genRandom(rand, t);
            if (t == 0) {
                inicial.rosenbrock();
            } else {
                inicial.rastrigin();
            }
            eval++;
            inicial.eval = eval;
            inicial.iter = iter;
            swarm.add(inicial);
        }
        Particula.vecindario(swarm);

        Particula.sort(swarm);
        solucion = swarm.get(0);
        while (iter < P5.MAXITER[t]) {
            iter++;
            for (int i = 0; i < P5.NUMP; i++) {

                Particula actual = swarm.get(i);
                switch (tipo) {
                    case 0:
                        actual.moverA(rand, t);
                        break;
                    case 1:
                        actual.moverB(rand, t);
                        break;
                    default:
                        throw new AssertionError();
                }

                if (t == 0) {
                    actual.rosenbrock();
                } else {
                    actual.rastrigin();
                }
                eval++;
                actual.eval = eval;
                actual.iter = iter;
            }

            g.Grafo(swarm, t);
            g.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            g.setBounds(1200, 100, 600, 600);
            g.setTitle("PSO/" + nombre[tipo] + " - " + P5.P[t] + "/S" + SEED);
            g.setVisible(true);
            Thread.sleep(500);

            Particula.sort(swarm);
            Particula candidata = swarm.get(0);
            if (solucion.z > candidata.z) {
                solucion = candidata;
            }
        }

    }

}
