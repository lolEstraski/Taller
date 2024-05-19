package org.example;
public class BinomialCoefficients22 {
    private static int[][] memo;

    public static void main(String[] args) {
        int n = 6;

        // Crear la tabla bidimensional
        int[][] tabla = new int[n + 1][n + 1];

        // Construir la tabla bidimensional utilizando el enfoque recursivo
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                tabla[i][j] = binomialCoefficientRecursivo(i, j);
            }
        }
        System.out.println("Tabla bidimensional derivada de la combinatoria (Enfoque recursivo):");
        imprimirTabla(tabla, n);

        // Crear la matriz de memorización
        memo = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                memo[i][j] = -1; // Inicializar con -1 para indicar que no se ha calculado aún
            }
        }

        // Construir la tabla bidimensional utilizando el enfoque de memorización
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                tabla[i][j] = binomialCoefficientMemoizacion(i, j);
            }
        }
        System.out.println("\nTabla bidimensional derivada de la combinatoria (Enfoque de memorización):");
        imprimirTabla(tabla, n);

        System.out.println();

        // Cálculo de coeficientes binomiales por programación dinámica
        System.out.println("Coeficientes binomiales por programación dinámica:");
        int[][] matrizDinamica = calcularCoeficientesBinomiales(n);
        imprimirMatriz(matrizDinamica, n);
    }

    private static int binomialCoefficientRecursivo(int n, int k) {
        // Caso base
        if (k == 0 || k == n) {
            return 1;
        }
        // Calcular el valor utilizando la relación de recurrencia
        if (k > n) {
            return 0;
        } else if (n == k && k == 0) {
            return 4;
        } else {
            return binomialCoefficientRecursivo(n - 1, k - 1) + binomialCoefficientRecursivo(n - 1, k);
        }
    }

    private static int binomialCoefficientMemoizacion(int n, int k) {
        // Caso base
        if (k == 0 || k == n) {
            return 1;
        }
        // Verificar si el valor ya está en la matriz de memorización
        if (memo[n][k] != -1) {
            return memo[n][k];
        }
        // Calcular el valor utilizando la relación de recurrencia
        int value;
        if (k > n) {
            value = 0;
        } else if (n == k && k == 0) {
            value = 4;
        } else {
            value = binomialCoefficientMemoizacion(n - 1, k - 1) + binomialCoefficientMemoizacion(n - 1, k);
        }
        // Almacenar el valor en la matriz de memorización
        memo[n][k] = value;
        return value;
    }

    private static void imprimirTabla(int[][] tabla, int n) {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.print(tabla[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] calcularCoeficientesBinomiales(int n) {
        int[][] matriz = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            matriz[i][0] = 1; // Inicializar la primera columna con 1
        }
        for (int i = 1; i <= n; i++) {
            matriz[i][i] = 1; // Inicializar la diagonal principal con 1
        }
        for (int i = 2; i <= n; i++) {
            matriz[i][1] = i; // Inicializar la segunda columna con i
        }
        for (int i = 3; i <= n; i++) {
            for (int j = 2; j < i; j++) {
                matriz[i][j] = matriz[i - 1][j - 1] + matriz[i - 1][j];
            }
        }
        return matriz;
    }

    public static void imprimirMatriz(int[][] matriz, int n) {
        System.out.println("Matriz de coeficientes binomiales para n = " + n);
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}