package org.example;

import java.util.Scanner;

public class devuelta23 {
    /**
     * Método para devolver el cambio en el cajero.
     */
    public static void devolver_cajero(int[] caja, int[] valores, int[] cantidades, int devolver) {
        int i = 0;
        while (devolver > 0) {
            if (valores[i] <= devolver && caja[i] > 0) {
                devolver -= valores[i];
                caja[i]--;
                cantidades[i]++;
            } else {
                i++;
            }
        }
    }
    /**
     * Método para calcular el total de dinero disponible en el cajero..
     */
    public static int calcularTotalCaja(int[] caja, int[] valores) {
        int total = 0;
        for (int i = 0; i < caja.length; i++) {
            total += caja[i] * valores[i];
        }
        return total;
    }
      /**
     * Método para validar que la cantidad ingresada sea divisible por 10000.
     * O(1),
     */
    public static int validarEntrada(int valorEntrada) throws Exception {
        if (valorEntrada % 10000 != 0) {
            throw new Exception("La cantidad ingresada no es divisible por 10000");
        } else {
            return valorEntrada;
        }
    }
    

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la cantidad a retirar: ");
        int cantidad_cambiar = scanner.nextInt();
        cantidad_cambiar = validarEntrada(cantidad_cambiar);
        
        int[] caja = { 50, 100, 200, 300 };
        int[] valores = { 100000, 50000, 20000, 10000 };
        int[] cantidades = new int[valores.length];
        
        int total = calcularTotalCaja(caja, valores);
        
        if (total > cantidad_cambiar) {
            try {
                devolver_cajero(caja, valores, cantidades, cantidad_cambiar);
            } catch (Exception exception) {
                System.out.println("el cajero no tiene disponible dicha cantidad");
            }
            
            System.out.println();
            System.out.println("Cantidad de billetes entregados por cada nominación:");
            for (int i = 0; i < valores.length; i++) {
                System.out.println("Billetes de " + valores[i] + " pesos: " + cantidades[i]);
            }
            
            System.out.println();
            System.out.println("Cantidad de billetes restantes en el cajero:");
            for (int i = 0; i < caja.length; i++) {
                System.out.println("Billetes de " + valores[i] + " pesos: " + caja[i]);
            }
        } else {
            System.out.println("Error: La cantidad que está solicitando cambiar no se encuentra disponible en el cajero.");
        }
    }
    
  
}