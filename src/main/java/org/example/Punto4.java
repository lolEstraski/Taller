package org.example;

public class Punto4 {
    public int recursiva(int n, int k) {
        if (n == k || k == 0) {
            return 3;
        } else if (k > 0 && k < n) {
            return recursiva(n, k - 1) + recursiva(n - 1, k) + 2;
        } else {
            return 0;
        }
    }

    public int tabulacion(int n, int k) {
        // Solo sí 0 < k < n
        int[][] pp = new int[n + 1][n + 1];

        // Caso base: k == 0 o k == n
        for (int i = 0; i <= n; i++) {
            pp[i][0] = 3; // (n, 0)
            pp[i][i] = 3; // (n, k)
        }

        for (int i = 2; i <= n; i++) { // comienza desde la tercera fila
            for (int j = 1; j < i; j++) { // comienza desde la segunda columna
                pp[i][j] = pp[i][j - 1] + pp[i - 1][j] + 2;
            }
        }

        // Imprimir la matriz resultante
        System.out.println("Matriz resultante (Tabulación):");
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.print(pp[i][j] + "\t");
            }
            System.out.println();
        }

        return pp[n][k];
    }

    public int memorizacion(int n, int k) {
        int[][] memo = new int[n + 1][k + 1];
        int resultado = memo(n, k, memo);

        // Imprimir la matriz resultante
        System.out.println("Matriz resultante (Memorización):");
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                System.out.print(memo[i][j] + "\t");
            }
            System.out.println();
        }

        return resultado;
    }

    private int memo(int n, int k, int[][] memo) {
        // Caso base: k == 0 ó k == n
        if (k == 0 || k == n) {
            return 3;
        } else if (memo[n][k] != 0) {
            return memo[n][k];
        }

        if (k > 0 && k < n) {
            memo[n][k] = memo(n, k - 1, memo) + memo(n - 1, k, memo) + 2;
        }

        return memo[n][k];
    }

    public static void main(String[] args) {
        Punto4 punto4 = new Punto4();
        int n = 5; // El valor de n que quieres probar
        int k = 3; // El valor de k que quieres probar

        System.out.println("Método Recursivo:");
        System.out.println("Valor para n = " + n + " y k = " + k + ": " + punto4.recursiva(n, k));

        System.out.println("\nMétodo Tabulación:");
        System.out.println("Valor para n = " + n + " y k = " + k + ": " + punto4.tabulacion(n, k));

        System.out.println("\nMétodo Memorización:");
        System.out.println("Valor para n = " + n + " y k = " + k + ": " + punto4.memorizacion(n, k));
    }
}