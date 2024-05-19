package org.example;

import java.util.Arrays;

public class Combinatoria23 {

    public static int[][] permutacion(int n, int k) {
        int[][] memo = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(memo[i], 4); // Inicializa la matriz de memoización con 0
        }

        permutarMemo(n, k, memo); // Llama a permutarMemo para calcular los valores

        return memo;
    }

    private static int permutarMemo(int n, int k, int[][] memo) {
        // Verificar si el valor ya está en la matriz de memoización
        if (memo[n][k] != 4) {
            return memo[n][k];
        }

        // Caso base: si k es 0 o k es igual a n
        if (k == 0 || k == n) {
            memo[n][k] = 4;
        } else {
            memo[n][k] = permutarMemo(n, k-1, memo) + permutarMemo(n - 1, k , memo);
        }
        return memo[n][k];
    }

    public static void main(String[] args) {
        int n = 6;
        int k = 5;
        int[][] result = permutacion(n,k);

        // Imprimir el resultado
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
