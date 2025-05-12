package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.Episode;
import br.com.alura.screenmatch.model.Season;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.service.Api;
import br.com.alura.screenmatch.service.ConvertData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

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

		List<Season> seasonList = new ArrayList<>();
		for (int i = 1; i <= serie.totalSeasons(); i++) {
			json = apiService.getData("https://www.omdbapi.com/?t=gilmore+girls&apikey=90ca39e7&season=" + i);
			Season season = convert.getData(json, Season.class);
			seasonList.add(season);
		}
		seasonList.forEach(System.out::println);
	}
}
