package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.model.Category;
import br.com.alura.screenmatch.model.Episode;
import br.com.alura.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTitleContainingIgnoreCase(String name);

    List<Serie> findByActorsContainingIgnoreCaseAndAvaliationGreaterThanEqual(String name, double avaliation);

    List<Serie> findTop5ByOrderByAvaliationDesc();

    List<Serie> findByGenreOrderByTitle(Category category);

    List<Serie> findByTotalSeasonsLessThanEqualAndAvaliationGreaterThanEqualOrderByTotalSeasons(int seasonLimit, Double minAvaliation);

    @Query("SELECT s FROM Serie s WHERE s.totalSeasons < :seasonLimit AND s.avaliation >= :minAvaliation order by s.totalSeasons")
    List<Serie> serieForMarathon(int seasonLimit, Double minAvaliation);

    @Query("SELECT e FROM Serie s JOIN s.episodes e WHERE e.title ILIKE %:text%")
    List<Episode> episodesFromText(String text);
}
