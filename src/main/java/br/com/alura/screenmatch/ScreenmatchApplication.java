package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.Episode;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.service.Api;
import br.com.alura.screenmatch.service.ConvertData;
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
		var apiService = new Api();
		var convert = new ConvertData();
		var json = apiService.getData("https://www.omdbapi.com/?t=gilmore+girls&apikey=90ca39e7");
		System.out.println(json);
		var serie = convert.getData(json, Serie.class);
		System.out.println(serie);

		json = apiService.getData("https://www.omdbapi.com/?t=gilmore+girls&apikey=90ca39e7&season=1&episode=2");
		Episode episode = convert.getData(json, Episode.class);
		System.out.println(episode);
	}
}
