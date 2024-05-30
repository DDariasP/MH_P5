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
                writer.write(P5.P[t] + ".tsp");

                writer.write("\nOPTIMA");

                writer.write("\nGREEDY");


                writer.write("\nSH - " + P5.MAXITER[t] + " iter");
                for (int i = 0; i < P5.SEED.length; i++) {
                }

                writer.write("\nSHE - " + P5.MAXITER[t] + " iter");
                for (int i = 0; i < P5.SEED.length; i++) {
                }

                writer.write("\n---------------------\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error en File.");
        }
    }

}
