package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.model.Episode;
import br.com.alura.screenmatch.model.SeasonData;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.model.SerieData;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.service.Api;
import br.com.alura.screenmatch.service.ConvertData;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private final String URL = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=90ca39e7";
    private final Scanner scanner = new Scanner(System.in);
    private final Api api = new Api();
    private final ConvertData convertData = new ConvertData();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private List<SerieData> serieData = new ArrayList<>();
    private SerieRepository repository;
    private List<Serie> serieList = new ArrayList<>();
    public Main(SerieRepository repository) {
        this.repository = repository;
    }

    public void showMenu() {
        var option = -1;
        while (option != 0) {
            var menu = """
                1 - Buscar series
                2 - Buscar episódios
                3 - Listar series
                4 - Buscar serie por titulo
                0 - sair
                """;
            System.out.println(menu);

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    searchSerieWeb();
                    break;
                case 2:
                    searchEpFromSerie();
                    break;
                case 3:
                    listSearchedSeries();
                    break;
                case 4:
                    searchSerieFromTitle();
                    break;
                case 0:
                    System.out.println("Encerrando.....");
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }
    }

    private void searchSerieWeb() {
        SerieData data = getSerieData();
        //serieData.add(data);
        repository.save(new Serie(data));
        System.out.println(data);
    }

    private SerieData getSerieData() {
        System.out.println("Digite o nome da serie para busca");
        var serieName = scanner.nextLine();
        var json = api.getData(URL + serieName.replace(" ", "+") + API_KEY);
        SerieData data = convertData.getData(json, SerieData.class);
        return data;
    }

    private void searchEpFromSerie() {
        listSearchedSeries();
        System.out.println("Digite a serie que deseja buscar os episodios");
        var serieName = scanner.nextLine();

        Optional<Serie> serie = repository.findByTitleContainingIgnoreCase(serieName);
        if(serie.isPresent()) {
            var searchedSerie = serie.get();
            List<SeasonData> seasonDataList = new ArrayList<>();

            for (int i = 1; i <= searchedSerie.getTotalSeasons() ; i++) {
                var json = api.getData(URL + searchedSerie.getTitle().replace(" ", "+") + "&season=" + i + API_KEY);
                SeasonData seasonData = convertData.getData(json, SeasonData.class);
                seasonDataList.add(seasonData);
            }
            seasonDataList.forEach(System.out::println);

            List<Episode> episodeList = seasonDataList.stream()
                    .flatMap(s -> s.episodeData().stream()
                            .map(e -> new Episode(s.number(), e)))
                    .toList();
            searchedSerie.setEpisodes(episodeList);
            repository.save(searchedSerie);
        } else {
            System.out.println("Serie nao encontrada!");
        }
    }

    private void listSearchedSeries() {
        serieList = repository.findAll();
        serieList.stream().sorted(Comparator.comparing(Serie::getGenre)).forEach(System.out::println);
    }

    private void searchSerieFromTitle() {
        System.out.println("Digite a serie que deseja buscar os episodios");
        var serieName = scanner.nextLine();
        Optional<Serie> resultSerie = repository.findByTitleContainingIgnoreCase(serieName);

        if (resultSerie.isPresent()) {
            System.out.println("Resultado para busca dessa serie: " + resultSerie.get().getTitle());
            System.out.println(resultSerie.get());
        } else {
            System.out.println("Serie nao encontrada");
        }
    }
}