package org.example;
import java.util.Arrays;

public class CoeficientePermutacion25 {

    public static void main(String[] args) {
        int n = 5; // Valor de n
        int k = 3; // Valor de k

        int[][] result = permutacion(n, k);

        System.out.println("Resultado de la permutación:");
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] permutacion(int n, int k) {
        int[][] c = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(c[i], -1); // Inicializar con valor -1 para indicar que no se ha calculado
        }
        return permutacionHelper(n, k, c);
    }

    private static int[][] permutacionHelper(int n, int k, int[][] c) {
        if (c[n][k] != -1) {
            return c;
        }

        if (k == 0 || n == 0) {
            c[n][k] = 1;
        } else {
            c[n][k] = permutacionHelper(n - 1, k, c)[n - 1][k] + k * permutacionHelper(n - 1, k - 1, c)[n - 1][k - 1];
        }

        if (k < c[n].length - 1) {
            return permutacionHelper(n, k + 1, c);
        } else if (n < c.length - 1) {
            return permutacionHelper(n + 1, 0, c);
        } else {
            return c;
        }
    }
}