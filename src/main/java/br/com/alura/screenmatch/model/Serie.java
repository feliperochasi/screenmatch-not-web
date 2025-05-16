package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.service.ChatGPT;

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
        this.plot = ChatGPT.getText(serieData.plot()).trim();
        this.language = serieData.language();
        this.awards = serieData.awards();
        this.poster = serieData.poster();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTotalSeasons() {
        return totalSeasons;
    }

    public void setTotalSeasons(Integer totalSeasons) {
        this.totalSeasons = totalSeasons;
    }

    public Double getAvaliation() {
        return avaliation;
    }

    public void setAvaliation(Double avaliation) {
        this.avaliation = avaliation;
    }

    public Category getGenre() {
        return genre;
    }

    public void setGenre(Category genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
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
