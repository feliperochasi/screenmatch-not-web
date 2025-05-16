package br.com.alura.screenmatch.model;

import java.util.OptionalDouble;

public class Serie {
    private String title;
    private Integer totalSeasons;
    private Double avaliation;
    private Category genre;
    private String director;
    private String actors;
    private String plot;
    private String language;
    private String awards;
    private String poster;

    public Serie(SerieData serieData) {
        this.title = serieData.title();
        this.totalSeasons = serieData.totalSeasons();
        this.avaliation = OptionalDouble.of(Double.parseDouble(serieData.avaliation())).orElse(0.0);//if else melhorado forma de atribuir valor padrao caso tenha qualquer erro
        this.genre = Category.fromString(serieData.genre().split(",")[0].trim());
        this.director = serieData.director();
        this.actors = serieData.actors();
        this.plot = serieData.plot();
        this.language = serieData.language();
        this.awards = serieData.awards();
        this.poster = serieData.poster();
    }

    @Override
    public String toString() {
        return "Serie{" +
                "title='" + title + '\'' +
                ", totalSeasons=" + totalSeasons +
                ", avaliation=" + avaliation +
                ", genre=" + genre +
                ", director='" + director + '\'' +
                ", actors='" + actors + '\'' +
                ", plot='" + plot + '\'' +
                ", language='" + language + '\'' +
                ", awards='" + awards + '\'' +
                ", poster='" + poster + '\'' +
                '}';
    }
}
