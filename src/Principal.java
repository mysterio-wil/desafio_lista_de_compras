import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        //System.out.println("Escriba el límite de la tarjeta: ");
        double limite = 0;

        //Obtener límite de la tarjeta

        while(true) {
            try {
                System.out.println("Escriba el límite de la tarjeta: ");
                limite = lectura.nextDouble();
                if (limite <= 0) {
                    System.out.println("El limite debe ser mayor que cero.");
                    continue;
                }
                break; // Salir del bucle si la entrada es válida
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número válido.");
                lectura.next(); // Limpiar el escáner
            }
        }

        TarjetaDeCredito tarjeta = new TarjetaDeCredito(limite);
        boolean continuar = true;

        while (continuar) {
            lectura.nextLine(); // Consumir el salto de línea

            // Obtener descripción de la compra
            System.out.println("Escriba la descripción de la compra:");
            String descripcion = lectura.nextLine();

            // Obtener valor de la compra
            double valor = 0;
            while (true) {
                try {
                    System.out.println("Escriba el valor de la compra:");
                    valor = lectura.nextDouble();
                    if (valor <= 0) {
                        System.out.println("El valor de la compra debe mayor que cero.");
                        continue;
                    } break; // Salir del bucle si la entrada es válidad
                } catch (InputMismatchException e) {
                    System.out.println("Por favor, ingrese un número válido");
                    lectura.next(); // Limpiar el escáner
                }
            }

            Compra compra = new Compra(valor, descripcion);
            boolean compraRealizada = tarjeta.lanzarCompra(compra);

            if (compraRealizada) {
                System.out.println("Compra realizada!");
                System.out.println("Escriba 0 para salir o 1 para continuar");
                continuar = lectura.nextInt() == 1;
            } else {
                System.out.println("Saldo insuficiente!");
                continuar = false; // Salir del bucle si hay saldo insuficiente
            }
        }

        System.out.println("***********************");
        System.out.println("COMPRAS REALIZADAS:\n");
        Collections.sort(tarjeta.getListaDeCompras());
        for (Compra compra : tarjeta.getListaDeCompras()) {
            System.out.println(compra.getDescripcion() + " - " +compra.getValor());
        }
        System.out.println("\n***********************");
        System.out.println("\nSaldo de la tarjeta: " +tarjeta.getSaldo());
    }
}
