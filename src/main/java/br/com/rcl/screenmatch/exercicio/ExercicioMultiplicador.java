package br.com.rcl.screenmatch.exercicio;

@FunctionalInterface
interface Multiplicador {
    int multiplicacao(int a, int b);
}

public class ExercicioMultiplicador {
    public static void main(String[] args) {
        Multiplicador mult = (a, b) -> a * b;
        System.out.println(mult.multiplicacao(5, 3));
    }
}
