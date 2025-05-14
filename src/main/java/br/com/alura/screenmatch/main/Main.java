package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.model.Episode;
import br.com.alura.screenmatch.model.EpisodeData;
import br.com.alura.screenmatch.model.SeasonData;
import br.com.alura.screenmatch.model.SerieData;
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
        var serie = convertData.getData(json, SerieData.class);
        System.out.println(serie);

        List<SeasonData> seasonDataList = new ArrayList<>();
        for (int i = 1; i <= serie.totalSeasons(); i++) {
            json = api.getData(URL + titleSerie.replace(" ", "+") + APIKEY + "&season=" + i);
            SeasonData seasonData = convertData.getData(json, SeasonData.class);
            seasonDataList.add(seasonData);
        }
        seasonDataList.forEach(System.out::println);

        seasonDataList.forEach(s -> s.episodeData().forEach(e -> System.out.println(e.title())));//lambdas no java

        List<EpisodeData> episodeDataList = seasonDataList.stream()
                .flatMap(s -> s.episodeData().stream())
                .collect(Collectors.toList());

        System.out.println("\n Top cinco episodios");
        episodeDataList.stream()
                .filter(e -> !e.avaliation().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(EpisodeData::avaliation).reversed())
                .limit(5)
                .forEach(System.out::println);

        List<Episode> episodeList = seasonDataList.stream()
                .flatMap(s -> s.episodeData().stream()
                        .map(e -> new Episode(s.number(), e)))
                .collect(Collectors.toList());
        System.out.println(episodeList);
    }
}
