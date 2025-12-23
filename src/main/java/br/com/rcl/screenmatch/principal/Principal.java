package br.com.rcl.screenmatch.principal;

import br.com.rcl.screenmatch.model.DadosEpisodio;
import br.com.rcl.screenmatch.model.DadosSerie;
import br.com.rcl.screenmatch.model.DadosTemporada;
import br.com.rcl.screenmatch.model.Episodio;
import br.com.rcl.screenmatch.service.ConsumoAPI;
import br.com.rcl.screenmatch.service.ConverteDados;

import java.security.cert.CertStoreException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    ConverteDados convesor = new ConverteDados();

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=2bf3b801";

    public void exibMenu(){
        System.out.println("Digite o nome da série para a busca");
        var nomeSerie = leitura.nextLine();
//        var nomeSerie = "two and a Half Men";
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = convesor.obterDados(json, DadosSerie.class);
        System.out.println(dados);
        List<DadosTemporada> temporadas = new ArrayList<>();

        for(int i=1; i <= dados.totalTemporada(); i++ ){
            json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = convesor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        //temporadas.forEach(t -> System.out.println(t));
        //a mesma forma para escrever
        temporadas.forEach(System.out::println);

//        for(int i = 0; i < dados.totalTemporada(); i++){
//            List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
//            for(int j = 0; j< episodiosTemporada.size(); j++){
//                System.out.println(episodiosTemporada.get(j).titulo());
//            }
//        }
        // a mesma forma de escrever
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println("Temporada: " +t.numero() + " Episódio: " + e.numero() + "-" + e.titulo())) );

//        System.out.println("\n Top 5 episódios");
//        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
//                .flatMap(t -> t.episodios().stream())
//                .collect(Collectors.toUnmodifiableList());
//        dadosEpisodios.stream()
//                .filter((e -> !e.avaliacao().equalsIgnoreCase("N/A")))
//                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
//                .peek(e ->System.out.println("ordenação "+ e))
//                .limit(5)
//                .forEach(System.out::println);


        System.out.println("\n\nEpisódios");
        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(), d))
                ).collect(Collectors.toList());
        episodios.forEach(System.out::println);

//        System.out.println("Digite um trecho do título do epsódio");
//        var trechoTitulo = leitura.nextLine();
//        // optinal = pode conter um valor único ou não ter valor nenhum
//        Optional<Episodio> epsodioBuscado = episodios.stream()
//                .filter(e -> e.getTitulo().toUpperCase().contains(trechoTitulo.toUpperCase()))
//                .findFirst();
//        if(epsodioBuscado.isPresent()){
//            System.out.println("Epsódio encontrado: " + epsodioBuscado.get().getTitulo());
//            System.out.println("Temporada: " + epsodioBuscado.get().getTemporada());
//        }else{
//            System.out.println("Epsódio não encontrado");
//        }

//
//        System.out.println("A partir de que ano você deseja ver os episódios? ");
//        var ano = leitura.nextInt();
//        leitura.nextLine();
//
//        LocalDate dataBusca = LocalDate.of(ano, 1, 1);
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        episodios.stream()
//                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
//                .forEach(e -> System.out.println("Temporada: " + e.getTemporada() +
//                        " Episódio: " + e.getTitulo() +
//                        " Data lançamento: " + e.getDataLancamento().format(formatter) ));

        // avalição por temporada
        Map<Integer, Double> avaliacaoPorTemporada = episodios.stream()
                .filter(f -> f.getAvaliacao() > 0)
                .collect(Collectors.groupingBy(Episodio::getTemporada, Collectors.averagingDouble(Episodio::getAvaliacao)));
//        System.out.println(avaliacaoPorTemporada);
        avaliacaoPorTemporada.forEach((key, value) -> System.out.println(key + ": " + value) );


        DoubleSummaryStatistics est = episodios.stream()
                .filter(f -> f.getAvaliacao() > 0.0)
                .collect(Collectors.summarizingDouble(Episodio::getAvaliacao));
        System.out.println(est);
        System.out.println("Número de temporadas: " + est.getCount());
        System.out.println("Melhor avalidação: " + est.getMax());
        System.out.println("Pior avaliação: " + est.getMin());
        System.out.println("Média das avaliações: " + est.getAverage());
    }
}
