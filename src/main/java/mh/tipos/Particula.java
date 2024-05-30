package mh.tipos;

import mh.P5;
import java.util.Random;
import static java.lang.Math.*;

/**
 *
 * @author diego
 */
public class Particula {

    public double x, y, z;
    public int eval, iter;
    public double[] velocidad;
    public Lista<Particula> vecinos, mejores;

    public Particula(double a, double b) {
        x = a;
        y = b;
        eval = -1;
        iter = -1;
        velocidad = new double[2];
        vecinos = new Lista<>();
        mejores = new Lista<>();
    }

    public void rosenbrock() {
        z = pow((1.0 - x), 2.0) + 100.0 * pow((y - pow(x, 2.0)), 2.0);
    }

    public void rastrigin() {
        z = 20.0 + pow(x, 2.0) + pow(y, 2.0) - 10.0 * (cos(2 * PI * x) + cos(2 * PI * y));
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
        String output = "[" + x + "," + y + "]";
        return output;
    }

}
