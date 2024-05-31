package mh;

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
                    writer.write("\n" + P5.BL[t][i].solucion.coste + "\t\t" + P5.BL[t][i].solucion.eval);
                }
                writer.write("\n---------------------\n");

                writer.write("\nPSO/B - " + P5.MAXITER[t] + " iter");
                for (int i = 0; i < P5.SEED.length; i++) {
                    writer.write("\n" + P5.PSO_B[t][i].solucion.coste + "\t\t" + P5.PSO_B[t][i].solucion.eval);
                }
                writer.write("\n---------------------\n");

                writer.write("\nPSO/R - " + P5.MAXITER[t] + " iter");
                for (int i = 0; i < P5.SEED.length; i++) {
                    writer.write("\n" + P5.PSO_R[t][i].solucion.coste + "\t\t" + P5.PSO_R[t][i].solucion.eval);
                }
                writer.write("\n---------------------\n");

                writer.write("\nPSO/S - " + P5.MAXITER[t] + " iter");
                for (int i = 0; i < P5.SEED.length; i++) {
                    writer.write("\n" + P5.PSO_S[t][i].solucion.coste + "\t\t" + P5.PSO_S[t][i].solucion.eval);
                }
                writer.write("\n---------------------\n\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error en File.");
        }
    }

}
