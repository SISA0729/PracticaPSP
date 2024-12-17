package Actividad2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InstalledPrograms {
    public static void main(String[] args) {
        String listPrograms;

        System.out.println(System.getProperties());
        if (System.getProperty("os.name").contains("Windows")) {
            listPrograms = "cmd.exe /c dir \"C:\\Program files\"";
        } else {
            listPrograms = "ls -l /bin";
        }


        ProcessBuilder processBuilder = new ProcessBuilder(listPrograms.split(" "));
        try {
            Process process = processBuilder.start();

            InputStream processInputStream = process.getInputStream();

            InputStream errorInputStream = process.getErrorStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorInputStream));
            String line = bufferedReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }

            bufferedReader = new BufferedReader(new InputStreamReader(processInputStream));
            line = bufferedReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            System.out.println("El proceso no puede ser lanzado.");
            e.printStackTrace();
        }
    }
}
