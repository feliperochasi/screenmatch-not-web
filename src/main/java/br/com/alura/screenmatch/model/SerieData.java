package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SerieData(@JsonAlias("Title") String title,
                        @JsonAlias("totalSeasons") Integer totalSeasons,
                        @JsonAlias("imdbRating") String avaliation,
                        @JsonAlias("Genre") String genre,
                        @JsonAlias("Director") String director,
                        @JsonAlias("Actors") String actors,
                        @JsonAlias("Plot") String plot,
                        @JsonAlias("Language") String language,
                        @JsonAlias("Awards") String awards,
                        @JsonAlias("Poster") String poster) {
}