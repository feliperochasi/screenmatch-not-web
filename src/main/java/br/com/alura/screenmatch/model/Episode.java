package br.com.alura.screenmatch.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Entity
@Table(name = "episodes")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer season;
    private String title;
    private Integer numberEp;
    private Double avaliation;
    private LocalDate releasedDate;
    @ManyToOne
    private Serie serie;

    public Episode(){};

    public Episode(Integer season, EpisodeData episode) {
        this.season = season;
        this.title = episode.title();
        this.numberEp = episode.numberEp();

        try {
            this.avaliation = Double.valueOf(episode.avaliation());
        } catch (NumberFormatException ex) {
            this.avaliation = 0.0;
        }

        try {
            this.releasedDate = LocalDate.parse(episode.releasedDate());
        } catch (DateTimeParseException ex) {
            this.releasedDate = null;
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumberEp() {
        return numberEp;
    }

    public void setNumberEp(Integer numberEp) {
        this.numberEp = numberEp;
    }

    public Double getAvaliation() {
        return avaliation;
    }

    public void setAvaliation(Double avaliation) {
        this.avaliation = avaliation;
    }

    public LocalDate getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(LocalDate releasedDate) {
        this.releasedDate = releasedDate;
    }

    @Override
    public String toString() {
        return "\n Episode{" +
                "season=" + season +
                ", title='" + title + '\'' +
                ", numberEp=" + numberEp +
                ", avaliation=" + avaliation +
                ", releasedDate=" + releasedDate;
    }
}
