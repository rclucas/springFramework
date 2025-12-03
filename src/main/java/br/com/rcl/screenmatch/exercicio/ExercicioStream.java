package br.com.rcl.screenmatch.exercicio;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ExercicioStream {
    public static void main(String[] args) {
//        filtrando();

//        modificando();

        parteFinalStream();
    }

    private static void parteFinalStream() {
        List<String> nomes = Arrays.asList("João", "Maria", "Pedro", "Ana");

        nomes.stream()
                .filter(s -> s.length() > 4)
                .forEach(nome -> System.out.println("Olá, " + nome + "!"));
        Integer qtdeRegistros = (int) nomes.stream()
                .filter(s -> s.length() > 4)
                .count();
        System.out.println(qtdeRegistros);
    }


    private static void modificando() {
        List<String> nomes = Arrays.asList("Java", "Stream", "Operações", "Intermediárias");

        List<Integer> tamanho = nomes.stream()
                .map(s -> s.length())
                .collect(Collectors.toUnmodifiableList());
        System.out.println(nomes);
        System.out.println(tamanho);
    }

    private static void filtrando() {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> numPar = numeros.stream()
                .filter(n -> n %2 ==0)
                .collect(Collectors.toUnmodifiableList());

        System.out.println(numeros);
        System.out.println(numPar);
    }
}
