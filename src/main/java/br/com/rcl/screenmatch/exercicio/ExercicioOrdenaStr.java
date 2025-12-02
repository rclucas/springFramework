package br.com.rcl.screenmatch.exercicio;

import java.util.Arrays;
import java.util.List;

public class ExercicioOrdenaStr {
    public static void main(String[] args) {
        List<String> nomes = Arrays.asList("Lucas", "Maria", "João", "Ana");
        nomes.sort((a, b) -> a.compareTo(b));
        System.out.println(nomes);
    }
}
