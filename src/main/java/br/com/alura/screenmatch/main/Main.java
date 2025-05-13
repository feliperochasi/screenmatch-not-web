package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.model.Episode;
import br.com.alura.screenmatch.model.Season;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.service.Api;
import br.com.alura.screenmatch.service.ConvertData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private final String URL = "https://www.omdbapi.com/?t=";
    private final String APIKEY = "&apikey=90ca39e7";
    private final Scanner scanner = new Scanner(System.in);
    private final Api api = new Api();
    private final ConvertData convertData = new ConvertData();

    public void showSerie() {
        System.out.println("Digite o nome da serie: ");
        var titleSerie = scanner.nextLine();
        var json = api.getData(URL + titleSerie.replace(" ", "+") + APIKEY);
        var serie = convertData.getData(json, Serie.class);
        System.out.println(serie);

        List<Season> seasonList = new ArrayList<>();
        for (int i = 1; i <= serie.totalSeasons(); i++) {
            json = api.getData(URL + titleSerie.replace(" ", "+") + APIKEY + "&season=" + i);
            Season season = convertData.getData(json, Season.class);
            seasonList.add(season);
        }
        seasonList.forEach(System.out::println);

        seasonList.forEach(s -> s.episodes().forEach(e -> System.out.println(e.title())));//lambdas no java

        List<Episode> episodeList = seasonList.stream()
                .flatMap(s -> s.episodes().stream())
                .collect(Collectors.toList());

        System.out.println("\n Top cinco episodios");
        episodeList.stream()
                .filter(e -> !e.avaliation().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(Episode::avaliation).reversed())
                .limit(5)
                .forEach(System.out::println);
    }
}
