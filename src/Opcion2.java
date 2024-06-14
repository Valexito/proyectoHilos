import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Opcion2 {
    public static void segundaOpcion() {
        String directorioEntrada = "sinProcesar";
        String directorioSalida = "Procesados";

        File dirEntrada = new File(directorioEntrada);
        File dirSalida = new File(directorioSalida);

        if (!dirSalida.exists()) {
            dirSalida.mkdirs();
        }

        File[] archivos = dirEntrada.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile()) {
                    procesarArchivo(archivo, directorioSalida);
                }
            }
        }

        System.out.println("Archivos procesados exitosamente.");
    }

    public static void procesarArchivo(File archivo, String directorioSalida) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        String fechaHora = sdf.format(new Date());

        String nombreArchivoSalida = archivo.getName().replace(".txt", "_PROC" + fechaHora + ".txt");
        File archivoSalida = new File(directorioSalida, nombreArchivoSalida);

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo));
             BufferedWriter writer = new BufferedWriter(new FileWriter(archivoSalida))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] valores = linea.split(",");
                int valor1 = Integer.parseInt(valores[0]);
                int valor2 = Integer.parseInt(valores[1]);
                int suma = valor1 + valor2;
                writer.write(valor1 + "+" + valor2 + "=" + suma);
                writer.newLine();
            }

        } catch (IOException e) {
            System.err.println("Error procesando el archivo: " + archivo.getName());
            e.printStackTrace();
        }

        // Mover el archivo original al directorio de salida
        try {
            Files.move(archivo.toPath(), new File(directorioSalida, archivo.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("Error moviendo el archivo: " + archivo.getName());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        segundaOpcion();
    }
}
