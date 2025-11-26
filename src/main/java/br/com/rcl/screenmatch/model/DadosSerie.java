package br.com.rcl.screenmatch.model;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosSerie(@JsonAlias("Title") String titulo,
                         @JsonAlias("totalSeasons") Integer totalSessoes,
                         @JsonAlias("imdbRating") String avaliacao) {
}
