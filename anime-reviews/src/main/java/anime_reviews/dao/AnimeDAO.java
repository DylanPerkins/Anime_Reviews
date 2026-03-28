package anime_reviews.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import anime_reviews.entity.Anime;

public interface AnimeDAO extends JpaRepository<Anime, Long> {

    List<Anime> findByTitleContainingIgnoreCase(String title);

    List<Anime> findByTags_TagNameIgnoreCase(String tagName);
}
