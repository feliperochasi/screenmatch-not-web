package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Episode(@JsonAlias("Title") String title,
                      @JsonAlias("Episode") int numberEp,
                      @JsonAlias("imdbRating") String avaliation,
                      @JsonAlias("Released") String releasedDate) {
}
