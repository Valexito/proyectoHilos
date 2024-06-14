import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Opcion3 {
    public static void terceraOpcion() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Ingrese el nombre del archivo que desea consultar: ");
        String nombreArchivo = scan.nextLine();
        scan.close();

        String directorioEntrada = "sinProcesar";
        String directorioSalida = "Procesados";

        File archivoSinProcesar = new File(directorioEntrada, nombreArchivo);
        File archivoProcesado = new File(directorioSalida, nombreArchivo);

        if (archivoSinProcesar.exists()) {
            mostrarContenidoArchivo(archivoSinProcesar);
        } else if (archivoProcesado.exists()) {
            mostrarContenidoArchivo(archivoProcesado);
        } else {
            System.out.println("El archivo no se encuentra en los directorios especificados.");
        }
    }

    public static void mostrarContenidoArchivo(File archivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.err.println("Error leyendo el archivo: " + archivo.getName());
            e.printStackTrace();
        }
    }
}
