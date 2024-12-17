package Actividad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DirectorioActual {
    public static void main(String[] args) {
        String currentDirectory = "";
        if (System.getProperty("os.name").contains("Windows")) {
            currentDirectory = "cmd.exe /c cd";
        } else {
            currentDirectory = "pwd";
        }

        Process process = null;
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(currentDirectory.split(" "));
            process = processBuilder.start();

            InputStream inputStream = process.getInputStream();

            InputStream inputError = process.getErrorStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputError));
            String line = bufferedReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            line = bufferedReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (process != null)
                System.out.println("Proceso finalizado con valor de salida " + process.exitValue());
            else
                System.out.printf("No se puede obtener el valor de salida porque el proceso es nulo.");
        }
    }
}