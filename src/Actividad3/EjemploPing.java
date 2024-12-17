package Actividad3;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class EjemploPing {
    public static void main(String[] args) {
        ProcessBuilder pb = null;
        if (System.getProperty("os.name").contains("Windows")) {
            pb = new ProcessBuilder("ping", "google.com");
        } else {
            pb = new ProcessBuilder("ping", "-c 4", "google.com");
        }

        try {
            Process p = pb.start();

            InputStream inputStream = p.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            InputStream error = p.getErrorStream();
            InputStreamReader errorReader = new InputStreamReader(error);
            BufferedReader bufferedError = new BufferedReader(errorReader);

            String linea = bufferedError.readLine();
            while (linea != null) {
                System.out.println(linea);
                linea = bufferedError.readLine();
            }

            linea = bufferedReader.readLine();
            while (linea != null) {
                System.out.println(linea);
                linea = bufferedReader.readLine();
            }

        } catch (Exception e) {
            System.out.println("Problemita");
        }

    }
}