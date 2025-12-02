package br.com.rcl.screenmatch.exercicio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExercicioListaInteiro {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5);
        numeros.replaceAll(n -> n * 3);
        System.out.println(numeros);
    }
}
