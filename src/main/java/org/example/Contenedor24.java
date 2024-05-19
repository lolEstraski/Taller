package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Contenedor24 {
    private static double pesoMaximo;
    private static List<Double> pesos = new ArrayList<>();
    private static List<Double> valores = new ArrayList<>();
    private static List<Integer> cantidades = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el peso máximo soportado por el container: ");
        pesoMaximo = scanner.nextDouble();

        // Definir los objetos
        definirObjetos();

        while (true) {
            System.out.println("Menú:");
            System.out.println("1. Mostrar objetos seleccionados (maximizar valor/peso)");
            System.out.println("2. Mostrar valor de la carga");
            System.out.println("3. Maximizar valor sin tener en cuenta costo beneficio");
            System.out.println("4. Salir");
            System.out.println("Seleccione una opción:");

            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    maximizarValorPeso();
                    break;
                case 2:
                    mostrarValorCarga();
                    break;
                case 3:
                    maximizarValor();
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }

    /**
     * Método para definir los objetos con sus pesos, valores y cantidades.
     * Complejidad computacional: O(1)
     */

    private static void definirObjetos() {
        // Definir los pesos, valores y cantidades de los objetos
        pesos.add(10.0);
        valores.add(100.0);
        cantidades.add(5);

        pesos.add(20.0);
        valores.add(300.0);
        cantidades.add(3);

        pesos.add(30.0);
        valores.add(500.0);
        cantidades.add(2);

        pesos.add(40.0);
        valores.add(700.0);
        cantidades.add(1);
    }
    /**
     * Método para maximizar el valor de la carga considerando el costo-beneficio.
     * Complejidad computacional: O(n^2), donde n es la cantidad de objetos.
     */

    private static void maximizarValorPeso() {
        double pesoActual = 0;
        double valorCarga = 0;
        int cantidadTotalObjetosSeleccionados = 0;

        // Seleccionar objetos que no excedan el peso máximo y que estén disponibles
        while (pesoActual <= pesoMaximo && hayObjetosDisponibles()) {
            int indiceSeleccionado = -1;
            double mejorValorPeso = 0;

            for (int i = 0; i < pesos.size(); i++) {
                if (cantidades.get(i) > 0 && pesos.get(i) <= pesoMaximo - pesoActual) {
                    double valorPeso = valores.get(i) / pesos.get(i);
                    if (valorPeso > mejorValorPeso) {
                        mejorValorPeso = valorPeso;
                        indiceSeleccionado = i;
                    }
                }
            }

            if (indiceSeleccionado == -1) break;

            double pesoDisponible = pesoMaximo - pesoActual;
            double cantidadFraccionada = Math.min(cantidades.get(indiceSeleccionado), pesoDisponible / pesos.get(indiceSeleccionado));
            double cantidadTotal = cantidadFraccionada;
            pesoActual += cantidadFraccionada * pesos.get(indiceSeleccionado);
            valorCarga += cantidadFraccionada * valores.get(indiceSeleccionado);
            cantidades.set(indiceSeleccionado, cantidades.get(indiceSeleccionado) - (int) cantidadFraccionada);
            cantidadTotalObjetosSeleccionados += cantidadTotal;

            // Mostrar el objeto seleccionado y la cantidad total
            System.out.println("Objeto " + (indiceSeleccionado + 1) + ": Peso: " + pesos.get(indiceSeleccionado) + ", Valor: " + valores.get(indiceSeleccionado) + ", Cantidad: " + cantidadTotal);
        }

        // Mostrar el valor total de la carga y la cantidad total de objetos seleccionados
        System.out.println("Cantidad total de objetos seleccionados: " + cantidadTotalObjetosSeleccionados);
        System.out.println("Valor de la carga: " + valorCarga);
    }


    /**
     * Método para maximizar el valor de la carga sin considerar el costo-beneficio.
     * Complejidad computacional: O(n^2), donde n es la cantidad de objetos.
     */
    private static void maximizarValor() {
        double pesoActual = 0;
        double valorCarga = 0;
        int cantidadTotalObjetosSeleccionados = 0;

        // Seleccionar objetos que no excedan el peso máximo y que estén disponibles
        while (pesoActual <= pesoMaximo && hayObjetosDisponibles()) {
            int indiceSeleccionado = -1;
            double mejorValor = 0;

            for (int i = 0; i < valores.size(); i++) {
                if (cantidades.get(i) > 0 && pesos.get(i) <= pesoMaximo - pesoActual) {
                    if (valores.get(i) > mejorValor) {
                        mejorValor = valores.get(i);
                        indiceSeleccionado = i;
                    }
                }
            }

            if (indiceSeleccionado == -1) break;

            double pesoDisponible = pesoMaximo - pesoActual;
            double cantidadObjeto = Math.min(cantidades.get(indiceSeleccionado), pesoDisponible / pesos.get(indiceSeleccionado));
            double cantidadTotal = cantidadObjeto;
            pesoActual += cantidadTotal * pesos.get(indiceSeleccionado);
            valorCarga += cantidadTotal * valores.get(indiceSeleccionado);
            cantidades.set(indiceSeleccionado, cantidades.get(indiceSeleccionado) - (int) cantidadObjeto);
            cantidadTotalObjetosSeleccionados += cantidadTotal;

            // Mostrar el objeto seleccionado y la cantidad total
            System.out.println("Objeto " + (indiceSeleccionado + 1) + ": Peso: " + pesos.get(indiceSeleccionado) + ", Valor: " + valores.get(indiceSeleccionado) + ", Cantidad: " + cantidadTotal);
        }

        // Mostrar el valor total de la carga y la cantidad total de objetos seleccionados
        System.out.println("Cantidad total de objetos seleccionados: " + cantidadTotalObjetosSeleccionados);
        System.out.println("Valor de la carga: " + valorCarga);
    }
     /**
     * Método para verificar si hay objetos disponibles.
     * Complejidad computacional: O(n), donde n es la cantidad de objetos.
     */

    private static boolean hayObjetosDisponibles() {
        for (int cantidad : cantidades) {
            if (cantidad > 0) return true;
        }
        return false;
    }


    /**     
     * Método para mostrar el valor total de la carga.
     * Complejidad computacional: O(n), donde n es la cantidad de objetos.
     */
    private static void mostrarValorCarga() {
        double valorCarga = 0;
        for (Double valor : valores) {
            valorCarga += valor;
        }
        System.out.println("Valor de la carga: " + valorCarga);
    }
}
