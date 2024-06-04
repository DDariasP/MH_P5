package mh;

import mh.tipos.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author diego
 */
public class Parser {

    public static void escribir(String filename) {
        try {
            File resultados = new File(filename);
            if (resultados.exists()) {
                resultados.delete();
                System.out.println("\nArchivo " + resultados.getName() + " sobreescrito.\n");
            } else {
                System.out.println("\nArchivo " + resultados.getName() + " creado.\n");
            }
            resultados.createNewFile();
            FileWriter writer = new FileWriter(filename);

            for (int t = 0; t < P5.P.length; t++) {
                writer.write("---------------------");
                writer.write("\n" + P5.P[t]);
                writer.write("\n---------------------\n");

                writer.write("\nBL - " + P5.MAXITER[t] + " iter");
                for (int i = 0; i < P5.SEED.length; i++) {
                    Particula s = P5.BL[t][i].s;
                    writer.write("\n" + s.coste + "\t\t" + s.eval);
                }
                writer.write("\n---------------------\n");

                writer.write("\nPSOLocal - " + P5.MAXITER[t] + " iter");
                for (int i = 0; i < P5.SEED.length; i++) {
                    Particula s = P5.PSOLocal[t][i].gBest.get(0);
                    writer.write("\n" + s.coste + "\t\t" + s.eval);
                }
                writer.write("\n---------------------\n");

                writer.write("\nPSOGlobal - " + P5.MAXITER[t] + " iter");
                for (int i = 0; i < P5.SEED.length; i++) {
                    Particula s = P5.PSOGlobal[t][i].gBest.get(0);
                    writer.write("\n" + s.coste + "\t\t" + s.eval);
                }
                writer.write("\n---------------------\n\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error en File.");
        }
    }

}
