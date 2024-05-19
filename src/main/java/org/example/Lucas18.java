package org.example;

import java.util.HashMap;
import java.util.Map;

public class Lucas18 {
    private static Map<Integer, Integer> memo = new HashMap<>();
        public static void main(String[] args) {
            System.out.println("Lucas recursivo: ");
            recursivo(6);
            System.out.println("\nLucas memorización 2:");
            memo2(6);
        }
            

        public static void recursivo(int n) {
            for (int i = 0; i < n; i++) {
                System.out.print(lucasRecursivo(i) + " ");
            }
        }
    
        public static int lucasRecursivo(int n) {
            if (n == 0) {
                return 2;
            }
            if (n == 1) {
                return 1;
            }
            return lucasRecursivo(n - 1) + lucasRecursivo(n - 2);
        }

        public static int lucasMemorizacion(int n) {
            if (memo.containsKey(n)) {
                return memo.get(n);
            }
    
            if (n == 0) {
                memo.put(n, 2);
                return 2;
            }
    
            if (n == 1) {
                memo.put(n, 1);
                return 1;
            }
    
            int result = lucasMemorizacion(n - 1) + lucasMemorizacion(n - 2);
            memo.put(n, result);
            return result;
        }
        private static void memo2(int n) {
            for (int i = 0; i <= n; i++) {
                System.out.print(lucasMemorizacion(i) + " ");
            }
        }

        /*  public static void memorizacion(int[] arreglo, int i) {
            arreglo[0] = 2;
            arreglo[1] = 1;
            System.out.print(arreglo[0] + " " + arreglo[1] + " ");
            if (i == arreglo.length) {
                return;
            } else {
                arreglo[i] = arreglo[i - 1] + arreglo[i - 2];
                System.out.print(arreglo[i] + " ");
                memorizacion(arreglo, i + 1);
            }
        }*/
    }
    
    

    
    //  public static void iterativo(int n) {
    //         int num1 = 2, num2 = 1, temp;
    //         System.out.print(num1 + " " + num2 + " ");
    //         for (int i = 2; i <= n; i++) {
    //             temp = num1 + num2;
    //             System.out.print(temp + " ");
    //             num2 = num1;
    //             num1 = temp;
    //         }
    //     }
    
        // public static void tabulacion(int[] arreglo) {
        //     arreglo[0] = 2;
        //     arreglo[1] = 1;
        //     System.out.print(arreglo[0] + " " + arreglo[1] + " ");
        //     for (int i = 2; i < arreglo.length; i++) {
        //         arreglo[i] = arreglo[i - 1] + arreglo[i - 2];
        //         System.out.print(arreglo[i] + " ");
        //     }
        // }
      // System.out.println("\nLucas iterativo: ");
            // iterativo(5);
            // System.out.println("\nLucas tabulación: ");
            // tabulacion(arreglo);
            //System.out.println("\nLucas memoización: ");
            //memorizacion(arreglo, 2);
       

