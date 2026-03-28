package anime_reviews.controller.model;

import java.util.HashSet;
import java.util.Set;

import anime_reviews.entity.AnimeReview;
import anime_reviews.entity.Users;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsersData {
    private Long userId;
    @NotBlank(message = "Username is required")
    private String username;
    private Set<Long> watchedAnime;
    private Set<Long> watchingAnime;
    private Set<Long> wantToWatch;
    private Set<Long> wontWatch;
    private Set<AnimeReviewData> animeReviews = new HashSet<>();

    public UsersData(Users user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.watchedAnime = user.getWatchedAnime();
        this.watchingAnime = user.getWatchingAnime();
        this.wantToWatch = user.getWantToWatch();
        this.wontWatch = user.getWontWatch();

        // Null checking
        removeNullValues();

        if (user.getAnimeReviews() != null) {
            for (AnimeReview review : user.getAnimeReviews()) {
                AnimeReviewData reviewData = new AnimeReviewData(review);

                this.animeReviews.add(reviewData);
            }
        }
    }

    public UsersData(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public Users toUsers() {
        Users user = new Users();

        user.setUserId(this.userId);
        user.setUsername(this.username);
        user.setWatchedAnime(this.watchedAnime != null ? this.watchedAnime : new HashSet<>());
        user.setWatchingAnime(this.watchingAnime != null ? this.watchingAnime : new HashSet<>());
        user.setWantToWatch(this.wantToWatch != null ? this.wantToWatch : new HashSet<>());
        user.setWontWatch(this.wontWatch != null ? this.wontWatch : new HashSet<>());

        if (this.animeReviews != null) {
            for (AnimeReviewData reviewData : this.animeReviews) {
                AnimeReview review = reviewData.toAnimeReview(user, null);

                user.getAnimeReviews().add(review);
            }
        }

        return user;
    }

    public void removeNullValues() {
        if (this.watchedAnime == null) {
            this.watchedAnime = new HashSet<>();
        }

        if (this.watchingAnime == null) {
            this.watchingAnime = new HashSet<>();
        }

        if (this.wantToWatch == null) {
            this.wantToWatch = new HashSet<>();
        }

        if (this.wontWatch == null) {
            this.wontWatch = new HashSet<>();
        }
    }

    public UsersData(Long userId, String username, Set<Long> watchedAnime, Set<Long> watchingAnime,
            Set<Long> wantToWatch, Set<Long> wontWatch, Set<AnimeReview> animeReviews) {
        this.userId = userId;
        this.username = username;
        this.watchedAnime = watchedAnime;
        this.watchingAnime = watchingAnime;
        this.wantToWatch = wantToWatch;
        this.wontWatch = wontWatch;

        if (animeReviews != null) {
            for (AnimeReview review : animeReviews) {
                AnimeReviewData reviewData = new AnimeReviewData(review);
                this.animeReviews.add(reviewData);
            }
        }
    }

}
