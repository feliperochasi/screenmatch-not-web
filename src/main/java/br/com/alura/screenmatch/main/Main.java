package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.model.SeasonData;
import br.com.alura.screenmatch.model.SerieData;
import br.com.alura.screenmatch.service.Api;
import br.com.alura.screenmatch.service.ConvertData;

import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {

    private final String URL = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=90ca39e7";
    private final Scanner scanner = new Scanner(System.in);
    private final Api api = new Api();
    private final ConvertData convertData = new ConvertData();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void showMenu() {
        var menu = """
                1 - Buscar series
                2 - Buscar episódios
                
                0 - sair
                """;
        System.out.println(menu);

        var option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                searchSerieWeb();
                break;
            case 2:
                searchEpFromSerie();
                break;
            case 0:
                System.out.println("Encerrando.....");
                break;
            default:
                System.out.println("Opcao invalida");
        }
    }

    private void searchSerieWeb() {
        SerieData data = getSerieData();
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
        SerieData serieData = getSerieData();
        List<SeasonData> seasonDataList = new ArrayList<>();

        for (int i = 1; i <= serieData.totalSeasons() ; i++) {
            var json = api.getData(URL + serieData.title().replace(" ", "+") + "&season=" + i + API_KEY);
            SeasonData seasonData = convertData.getData(json, SeasonData.class);
            seasonDataList.add(seasonData);
        }

        seasonDataList.forEach(System.out::println);
    }
}