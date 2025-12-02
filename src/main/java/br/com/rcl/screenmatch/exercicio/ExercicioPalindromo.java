package br.com.rcl.screenmatch.exercicio;

interface Palindromos{
    boolean verificarPalindromo(String str);
}

public class ExercicioPalindromo {
    public static void main(String[] args) {
        Palindromos palindromos = str -> str.equals(new StringBuilder(str).reverse().toString());
        System.out.println(palindromos.verificarPalindromo("radar"));  // Resultado: true
        System.out.println(palindromos.verificarPalindromo("java"));   // Resultado: false
        System.out.println(palindromos.verificarPalindromo("ovo"));
    }
}
