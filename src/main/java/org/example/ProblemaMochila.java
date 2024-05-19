package org.example;

public class ProblemaMochila {
    public static void main(String[] args) {
        int[] valores = {2, 5, 10, 14, 15};
        int[] pesos = {1, 3, 4, 5, 7};
        int pesoMaximo = 8;

        int[][] dp = llenarMatrizDP(valores, pesos, pesoMaximo);
        imprimirMatrizDP(dp, valores.length, pesoMaximo);

        int valorMaximo = dp[valores.length][pesoMaximo];
        String objetosSeleccionados = obtenerObjetosSeleccionados(dp, valores, pesos, pesoMaximo);

        System.out.println("\nValor máximo: " + valorMaximo);
        System.out.println("Objetos seleccionados: " + objetosSeleccionados);
    }

    private static int[][] llenarMatrizDP(int[] valores, int[] pesos, int pesoMaximo) {
        int n = valores.length;
        int[][] dp = new int[n + 1][pesoMaximo + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= pesoMaximo; w++) {
                int valorSinObjeto = dp[i - 1][w];
                int valorConObjeto = (pesos[i - 1] <= w) ? valores[i - 1] + dp[i - 1][w - pesos[i - 1]] : 0;
                dp[i][w] = Math.max(valorSinObjeto, valorConObjeto);
            }
        }

        return dp;
    }

   
    private static String obtenerObjetosSeleccionados(int[][] dp, int[] valores, int[] pesos, int pesoMaximo) {
        int n = valores.length;
        int pesoRestante = pesoMaximo;
        StringBuilder objetos = new StringBuilder();

        for (int i = n; i > 0; i--) {
            if (dp[i][pesoRestante] != dp[i - 1][pesoRestante]) {
                objetos.append((char) ('A' + i - 1));
                pesoRestante -= pesos[i - 1];
            }
        }

        return objetos.toString();
    }

    private static void imprimirMatrizDP(int[][] dp, int n, int pesoMaximo) {
        System.out.println("Matriz dp:");
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= pesoMaximo; w++) {
                System.out.print(dp[i][w] + "\t");
            }
            System.out.println();
        }
    }

}