package br.com.rcl.screenmatch.exercicio;

interface Transformar{
    String transformar(String s);
}

public class ExercicioTransformador {
    public static void main(String[] args) {
        Transformar toUpperCase = s -> s.toUpperCase();
        System.out.println(toUpperCase.transformar("java"));
    }
}
