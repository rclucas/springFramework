package br.com.rcl.screenmatch;

import br.com.rcl.screenmatch.model.DadosSerie;
import br.com.rcl.screenmatch.service.ConsumoAPI;
import br.com.rcl.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ConsumoAPI consumoAPI = new ConsumoAPI();
		var json = consumoAPI.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=2bf3b801");
		System.out.println(json);
		ConverteDados convesor = new ConverteDados();
		DadosSerie dados = convesor.obterDados(json, DadosSerie.class);
		System.out.println(dados);

//		json = consumoAPI.obterDados("https://coffee.alexflipnote.dev/random.json");
//		System.out.println(json);
	}
}
