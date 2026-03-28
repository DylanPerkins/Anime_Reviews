package anime_reviews.controller.model;

import java.util.HashSet;
import java.util.Set;

import anime_reviews.entity.Anime;
import anime_reviews.entity.Tags;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnimeData {
    private Long animeId;
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Animation studio is required")
    private String animationStudio;
    @NotNull(message = "Episode count is required")
    @Min(value = 1, message = "Episode count must be at least 1")
    private Integer episodeCount;
    @Valid
    private Set<TagsData> tags = new HashSet<>();

    public AnimeData(Anime anime) {
        this.animeId = anime.getAnimeId();
        this.title = anime.getTitle();
        this.animationStudio = anime.getAnimationStudio();
        this.episodeCount = anime.getEpisodeCount();

        if (anime.getTags() != null) {
            for (Tags tag : anime.getTags()) {
                TagsData tagData = new TagsData(tag);

                this.tags.add(tagData);
            }
        }

    }

    public AnimeData(Long animeId, String title, String animationStudio, Integer episodeCount) {
        this.animeId = animeId;
        this.title = title;
        this.animationStudio = animationStudio;
        this.episodeCount = episodeCount;
    }

    public Anime toAnime() {
        Anime anime = new Anime();

        anime.setAnimeId(this.animeId);
        anime.setTitle(this.title);
        anime.setAnimationStudio(this.animationStudio);
        anime.setEpisodeCount(this.episodeCount);

        if (this.tags != null) {
            for (TagsData tagData : this.tags) {
                Tags tag = tagData.toTags();

                anime.getTags().add(tag);
            }
        }

        return anime;
    }

}
