import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Opcion1 {
    
    public static void primeraOpcion() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Ingrese la cantidad de archivos a generar: ");
        int cantidadArchivos = scan.nextInt();
        scan.close();

        String directorio = "sinProcesar";

        File dir = new File(directorio);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        Thread[] threads = new Thread[cantidadArchivos];
        
        for (int i = 0; i < cantidadArchivos; i++) {
            String nombreArchivo = directorio + "/archivo" + (i + 1) + ".txt";
            threads[i] = new Thread(new GeneradorDeArchivo(nombreArchivo));
            threads[i].start();
        }

        for (int i = 0; i < cantidadArchivos; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.err.println("Error esperando a que el hilo termine: " + threads[i].getName());
                e.printStackTrace();
            }
        }

        System.out.println("Archivos generados exitosamente.");
    }
}

class GeneradorDeArchivo implements Runnable {
    private String nombreArchivo;

    public GeneradorDeArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    @Override
    public void run() {
        Random random = new Random();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (int i = 0; i < 1000; i++) {
                int valor1 = random.nextInt(100) + 1; 
                int valor2 = random.nextInt(100) + 1; 
                writer.write(valor1 + "," + valor2);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error escribiendo el archivo: " + nombreArchivo);
            e.printStackTrace();
        }
    }
}
