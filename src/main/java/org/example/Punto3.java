package org.example;
import java.util.Arrays;

public class Punto3 {

    public int recursivo(int n){
        if (n == 0){
            return 3;
        }else if (n == 1){
            return 2;
        }else if (n == 2){
            return 1;
        }else if (n > 2){
            return recursivo(n-1) + recursivo(n-2) + recursivo(n-3);
        }
        return n;
    }

    public int iterativo(int n){
        int valor = 0;

        if (n == 0){
            return 3;
        }else if (n == 1){
            return 2;
        }else if (n == 2){
            return 1;
        }else if (n > 2){
            int a = 1;
            int b = 2;
            int c = 3;
            for(int i=3; i<=n; i++){
                valor = a+b+c;
                c = b;
                b = a;
                a = valor;
            }
        }
        return valor;
    }

    public int tabulacion(int n){

        int[] aa = new int[n+1];

        if (n == 0){
            return 3;
        }else if (n == 1){
            return 2;
        }else if (n == 2){
            return 1;
        }else if (n > 2){   
            aa[0] = 3;
            aa[1] = 2;
            aa[2] = 1;
            for(int i=3; i<=n; i++){
                aa[i] = aa[i-1] + aa[i-2] + aa[i-3];
            }
        }

        return aa[n];
    }

    private int[] memo;
    
    public int memorizacion(int n){
        memo = new int[n+1];
        return memo(n);
    }

    private int memo(int n){

        if (n == 0){
            return 3;
        }else if (n == 1){
            return 2;
        }else if (n == 2){
            return 1;
        }else if (memo[n] > 2){
            return memo[n];
        }   
        memo[n] = memo(n-1) + memo(n-2) + memo(n-3);
        return memo[n];
    }
    public static void main(String[] args) {
        Punto3 punto3 = new Punto3();
        int n = 10; // El valor de n que quieres probar

        System.out.println("Método Recursivo:");
        System.out.println("Valor para n = " + n + ": " + punto3.recursivo(n));

        System.out.println("\nMétodo Iterativo:");
        System.out.println("Valor para n = " + n + ": " + punto3.iterativo(n));

        System.out.println("\nMétodo Tabulación:");
        int[] matriz = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            matriz[i] = punto3.tabulacion(i);
        }
        System.out.println("Matriz resultante: " + Arrays.toString(matriz));

        System.out.println("\nMétodo Memorización:");
        System.out.println("Valor para n = " + n + ": " + punto3.memorizacion(n));
    }

    
}