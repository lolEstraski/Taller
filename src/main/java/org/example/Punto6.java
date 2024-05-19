package org.example;

public class Punto6 {

    public int recursivo(int n, int k) {
        if (n == k || k == 0)
            return 2;
        else if (0 < k && k < n) {
            return recursivo(n, k - 1) + recursivo(n - 1, k - 1) + recursivo(n - 1, k);
        } else {
            return 0;
        }
    }

    public int iterativo(int n, int k) {
        // Solo sí 0 < k < n
        int[][] pp = new int[n + 1][n + 1];

        // Caso base: k == 0 o k == n
        for (int i = 0; i <= n; i++) {
            pp[i][0] = 2; // (n, 0)
            pp[i][i] = 2; // (n, k)
        }

        for (int i = 2; i <= n; i++) { // comienza desde la tercera fila, filas anteriores = 2
            for (int j = 1; j < i; j++) { // comienza desde la segunda columna, columna 1 = 2
                pp[i][j] = pp[i][j - 1] + pp[i - 1][j - 1] + pp[i - 1][j];
            }
        }

        // Imprimir el triángulo resultante
        System.out.println("Triángulo resultante (Iterativo):");
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(pp[i][j] + "\t");
            }
            System.out.println();
        }

        return pp[n][k];
    }

    public int tabular(int n, int k) {
        if (k <= n) {
            // Solo sí 0 < k < n
            int[][] pp = new int[n + 1][n + 1];

            // Caso base: k == 0 o k == n
            for (int i = 0; i <= n; i++) {
                pp[i][0] = 2; // (n, 0)
                pp[i][i] = 2; // (n, k)
            }

            for (int i = 2; i <= n; i++) { // comienza desde la tercera fila, filas anteriores = 2
                for (int j = 1; j < i; j++) { // comienza desde la segunda columna, columna 1 = 2
                    pp[i][j] = pp[i][j - 1] + pp[i - 1][j - 1] + pp[i - 1][j];
                }
            }

            // Imprimir el triángulo resultante
            System.out.println("Triángulo resultante (Tabular):");
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= i; j++) {
                    System.out.print(pp[i][j] + "\t");
                }
                System.out.println();
            }

            return pp[n][k];
        }

        // Valores no calculados
        return 0;
    }

    public int memorizacion(int n, int k) {
        int[][] memo = new int[n + 1][n + 1];
        int resultado = memo(n, k, memo);

        // Imprimir el triángulo resultante
        System.out.println("Triángulo resultante (Memorización):");
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(memo[i][j] + "\t");
            }
            System.out.println();
        }

        return resultado;
    }

    private int memo(int n, int k, int[][] memo) {
        if (n == k || k == 0)
            return 2;
        else if (memo[n][k] != 0) {
            return memo[n][k];
        }

        memo[n][k] = memo(n, k - 1, memo) + memo(n - 1, k - 1, memo) + memo(n - 1, k, memo);
        return memo[n][k];
    }

    public static void main(String[] args) {
        Punto6 punto6 = new Punto6();
        int n = 5; // El valor de n que quieres probar
        int k = 3; // El valor de k que quieres probar

        System.out.println("Método Recursivo:");
        System.out.println("Valor para n = " + n + " y k = " + k + ": " + punto6.recursivo(n, k));

        System.out.println("\nMétodo Iterativo:");
        System.out.println("Valor para n = " + n + " y k = " + k + ": " + punto6.iterativo(n, k));

        System.out.println("\nMétodo Tabular:");
        System.out.println("Valor para n = " + n + " y k = " + k + ": " + punto6.tabular(n, k));

        System.out.println("\nMétodo Memorización:");
        System.out.println("Valor para n = " + n + " y k = " + k + ": " + punto6.memorizacion(n, k));
    }
}