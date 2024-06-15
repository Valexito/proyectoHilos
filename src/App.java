import java.util.Scanner;

public class App {

    static int opcion;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("OPCIONES");
        System.out.print("Ingresa opcion:");

        opcion = scan.nextInt(); 
        
        switch (opcion) {
            case 1:
            Opcion1.primeraOpcion();
                break;
            case 2:
            Opcion2.segundaOpcion();
                break;
            case 3:
            Opcion3.terceraOpcion();
                break;
                
            default:
            System.out.println("Opción no válida");
                break;
        }

        scan.close(); 
    }
}
