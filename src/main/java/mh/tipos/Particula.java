package mh.tipos;

import mh.P5;
import java.util.Random;
import org.jfree.chart.util.ShapeUtils;
import java.awt.Color;
import java.awt.Shape;
import static java.lang.Math.*;
import java.text.DecimalFormat;

/**
 *
 * @author diego
 */
public class Particula {

    public static int N = 0;
    public static final Color[] C = {Color.MAGENTA, Color.GREEN, Color.YELLOW, Color.RED, Color.CYAN};
    public static final Shape[] F = {ShapeUtils.createDiamond(6), ShapeUtils.createDiagonalCross(4, 1)};
    public final int id;
    public final Color color;
    public final Shape forma;
    public double x, y, z;
    public String coste;
    public int eval, iter;
    public double[] velocidad;
    public Lista<Particula> vecinos, mejores;

    public Particula(double a, double b) {
        id = N;
        N++;
        color = C[id % C.length];
        forma = F[id % F.length];
        x = a;
        y = b;
        eval = -1;
        iter = -1;
        velocidad = new double[2];
        vecinos = new Lista<>();
        mejores = new Lista<>();
    }

    public Particula(Particula p) {
        id = p.id;
        color = p.color;
        forma = p.forma;
        x = p.x;
        y = p.y;
        eval = p.eval;
        iter = p.iter;
    }

    public void rosenbrock() {
        z = pow((1.0 - x), 2.0) + 100.0 * pow((y - pow(x, 2.0)), 2.0);
        coste = (new DecimalFormat("0.####E0")).format(z);
    }

    public void rastrigin() {
        z = 20.0 + pow(x, 2.0) + pow(y, 2.0) - 10.0 * (cos(2 * PI * x) + cos(2 * PI * y));
        coste = (new DecimalFormat("0.####E0")).format(z);
    }

    public void moverB(Random rand, int t) {

    }

    public void moverR(Random rand, int t) {

    }

    public void moverS(Random rand, int t) {

    }

    public static Particula genRandom(Random rand, int t) {
        double x = P5.MINX[t] + (P5.MAXX[t] - P5.MINX[t]) * rand.nextDouble();
        double y = P5.MINY[t] + (P5.MAXY[t] - P5.MINY[t]) * rand.nextDouble();
        Particula p = new Particula(x, y);

        double vx = P5.VMINX[t] + (P5.VMAXX[t] - P5.VMINX[t]) * rand.nextDouble();
        double vy = P5.VMINY[t] + (P5.VMAXY[t] - P5.VMINY[t]) * rand.nextDouble();
        p.velocidad[0] = vx;
        p.velocidad[1] = vy;

        return p;
    }

    public static Particula genVecino(Particula p, Random rand, int t) {
        double x, y;
        boolean suma;

        suma = rand.nextBoolean();
        if (suma) {
            x = p.x + 0.1 * rand.nextDouble();
        } else {
            x = p.x - 0.1 * rand.nextDouble();
        }
        if (x > P5.MAXX[t]) {
            x = P5.MAXX[t];
        }
        if (x < P5.MINX[t]) {
            x = P5.MINX[t];
        }

        suma = rand.nextBoolean();
        if (suma) {
            y = p.y + 0.1 * rand.nextDouble();
        } else {
            y = p.y - 0.1 * rand.nextDouble();
        }
        if (y > P5.MAXY[t]) {
            y = P5.MAXY[t];
        }
        if (y < P5.MINY[t]) {
            y = P5.MINY[t];
        }

        Particula vecino = new Particula(x, y);
        return vecino;
    }

    public static void vecindario(Lista<Particula> swarm) {
        int tam = swarm.size();
        for (int i = 0; i < tam; i++) {
            Particula p = swarm.get(i);
            for (int v = 1; v <= P5.VECIN; v++) {
                int pos;
                pos = (i + v) % tam; //resto
                p.vecinos.add(swarm.get(pos));
                pos = (((i - v) % tam) + tam) % tam; //modulo
                p.vecinos.add(swarm.get(pos));
            }
        }
    }

    public static void sort(Lista<Particula> lista) {
        if (lista == null || lista.isEmpty()) {
            return;
        }
        quickSort(lista, 0, lista.size() - 1);
    }

    private static void quickSort(Lista<Particula> lista, int menor, int mayor) {
        if (menor < mayor) {
            int indexPivote = partition(lista, menor, mayor);
            quickSort(lista, menor, indexPivote - 1);
            quickSort(lista, indexPivote + 1, mayor);
        }
    }

    private static int partition(Lista<Particula> lista, int menor, int mayor) {
        Particula pivote = lista.get(mayor);
        int i = menor - 1;
        for (int j = menor; j < mayor; j++) {
            if (lista.get(j).z - pivote.z <= 0) {
                i++;
                swap(lista, i, j);
            }
        }
        swap(lista, i + 1, mayor);
        return (i + 1);
    }

    private static void swap(Lista<Particula> lista, int i, int j) {
        Particula tmp = lista.get(i);
        lista.replace(i, lista.get(j));
        lista.replace(j, tmp);
    }

    @Override
    public String toString() {
        String output = String.valueOf(id);
        return output;
    }

}
