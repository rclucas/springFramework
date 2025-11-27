package br.com.rcl.screenmatch.principal;

import br.com.rcl.screenmatch.model.DadosSerie;
import br.com.rcl.screenmatch.model.DadosTemporada;
import br.com.rcl.screenmatch.service.ConsumoAPI;
import br.com.rcl.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    ConverteDados convesor = new ConverteDados();

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=2bf3b801";

    public void exibMenu(){
        System.out.println("Digite o nome da série para a busca");
//        var nomeSerie = leitura.nextLine();
        var nomeSerie = "two and a Half Men";
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
    }
}
