package anime_reviews.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import anime_reviews.controller.model.AnimeData;
import anime_reviews.controller.model.AnimeReviewData;
import anime_reviews.controller.model.TagsData;
import anime_reviews.controller.model.UsersData;
import anime_reviews.service.AnimeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/anime-reviews", produces = "application/json")
@Slf4j
public class AnimeController {

    @Autowired
    private AnimeService animeService;

    // Create a new anime
    @PostMapping("/anime")
    @ResponseStatus(HttpStatus.CREATED)
    public AnimeData createAnime(@Valid @RequestBody AnimeData animeData) {
        log.info("Creating anime: {}", animeData);

        return animeService.saveAnime(animeData);
    }

    // Update an anime
    @PutMapping("/anime/{animeId}")
    public AnimeData updateAnime(@PathVariable Long animeId, @Valid @RequestBody AnimeData animeData) {
        animeData.setAnimeId(animeId);

        log.info("Updating anime: {}", animeData);

        return animeService.saveAnime(animeData);
    }

    // Get all anime and associated tags (if any)
    @GetMapping("/anime")
    public List<AnimeData> getAllAnime() {
        log.info("Getting all anime info");

        return animeService.retrieveAllAnime();
    }

    // Get a specific anime and associated tags (if any)
    @GetMapping("/anime/{animeId}")
    public AnimeData retrieveAnime(@PathVariable Long animeId) {
        log.info("Getting anime: {}", animeId);

        return animeService.retrieveAnimeById(animeId);
    }

    // Create a new tag
    @PostMapping("/tag")
    @ResponseStatus(HttpStatus.CREATED)
    public TagsData createTag(@Valid @RequestBody TagsData tagsData) {
        log.info("Creating tag: {}", tagsData);

        return animeService.saveTag(tagsData);
    }

    // Update a tag
    @PutMapping("/tag/{tagId}")
    public TagsData updateTag(@PathVariable Long tagId, @Valid @RequestBody TagsData tagsData) {
        tagsData.setTagId(tagId);

        log.info("Updating tag: {}", tagsData);

        return animeService.saveTag(tagsData);
    }

    // Get a tag's info
    @GetMapping("/tag/{tagId}")
    public TagsData retrieveTag(@PathVariable Long tagId) {
        log.info("Getting tag: {}", tagId);

        return animeService.retrieveTagById(tagId);
    }

    // Add a tag to an anime
    @PostMapping("/anime/{animeId}/tag/{tagId}")
    @ResponseStatus(HttpStatus.CREATED)
    public AnimeData addTagToAnime(@PathVariable Long animeId, @PathVariable Long tagId) {
        log.info("Adding tag {} to anime {}", tagId, animeId);

        return animeService.addTagToAnime(animeId, tagId);
    }

    // Create a new user
    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public UsersData createUser(@Valid @RequestBody UsersData usersData) {
        log.info("Creating user: {}", usersData);

        return animeService.saveUser(usersData);
    }

    // Update a user's info
    @PutMapping("/user/{userId}")
    public UsersData updateUser(@PathVariable Long userId, @Valid @RequestBody UsersData usersData) {
        usersData.setUserId(userId);

        log.info("Updating user: {}", usersData);

        return animeService.saveUser(usersData);
    }

    // Get all of a user's info
    @GetMapping("/user/{userId}")
    public UsersData retrieveUser(@PathVariable Long userId) {
        log.info("Getting user: {}", userId);

        return animeService.retrieveUserById(userId);
    }

    // The following 4 POST requests function to add anime to user's list

    // Add anime to user's watched list
    @PostMapping("/user/{userId}/watched/{animeId}")
    @ResponseStatus(HttpStatus.CREATED)
    public UsersData addWatchedAnime(@PathVariable Long userId, @PathVariable Long animeId) {
        log.info("Adding anime {} to user {}'s watched list", animeId, userId);

        return animeService.addWatchedAnime(userId, animeId);
    }

    // Add anime to user's watching list
    @PostMapping("/user/{userId}/watching/{animeId}")
    @ResponseStatus(HttpStatus.CREATED)
    public UsersData addWatchingAnime(@PathVariable Long userId, @PathVariable Long animeId) {
        log.info("Adding anime {} to user {}'s watching list", animeId, userId);

        return animeService.addWatchingAnime(userId, animeId);
    }

    // Add anime to user's want to watch list
    @PostMapping("/user/{userId}/want-to-watch/{animeId}")
    @ResponseStatus(HttpStatus.CREATED)
    public UsersData addWantToWatchAnime(@PathVariable Long userId, @PathVariable Long animeId) {
        log.info("Adding anime {} to user {}'s want to watch list", animeId, userId);

        return animeService.addWantToWatchAnime(userId, animeId);
    }

    // Add anime to user's wont watch list
    @PostMapping("/user/{userId}/wont-watch/{animeId}")
    @ResponseStatus(HttpStatus.CREATED)
    public UsersData addWontWatchAnime(@PathVariable Long userId, @PathVariable Long animeId) {
        log.info("Adding anime {} to user {}'s wont watch list", animeId, userId);

        return animeService.addWontWatchAnime(userId, animeId);
    }

    // The following 4 DELETE requests function to remove anime from user's list

    // Remove an anime from user's watched list
    @DeleteMapping("/user/{userId}/watched/{animeId}")
    public ResponseEntity<Map<String, Object>> removeWatchedAnime(@PathVariable Long userId, @PathVariable Long animeId) {
        log.info("Removing anime {} from user {}'s watched list", animeId, userId);
        Set<Long> updatedList = animeService.removeWatchedAnimeByID(userId, animeId);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Anime ID " + animeId + " has been removed from your watched list.");
        response.put("watchedAnime", updatedList);
        return ResponseEntity.ok(response);
    }

    // Remove an anime from user's watching list
    @DeleteMapping("/user/{userId}/watching/{animeId}")
    public ResponseEntity<Map<String, Object>> removeWatchingAnime(@PathVariable Long userId, @PathVariable Long animeId) {
        log.info("Removing anime {} from user {}'s watching list", animeId, userId);
        Set<Long> updatedList = animeService.removeWatchingAnimeByID(userId, animeId);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Anime ID " + animeId + " has been removed from your watching list.");
        response.put("watchingAnime", updatedList);
        return ResponseEntity.ok(response);
    }

    // Remove an anime from user's want to watch list
    @DeleteMapping("/user/{userId}/want-to-watch/{animeId}")
    public ResponseEntity<Map<String, Object>> removeWantToWatchAnime(@PathVariable Long userId, @PathVariable Long animeId) {
        log.info("Removing anime {} from user {}'s want to watch list", animeId, userId);
        Set<Long> updatedList = animeService.removeWantToWatchAnimeByID(userId, animeId);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Anime ID " + animeId + " has been removed from your want to watch list.");
        response.put("wantToWatch", updatedList);
        return ResponseEntity.ok(response);
    }

    // Remove an anime from user's wont watch list
    @DeleteMapping("/user/{userId}/wont-watch/{animeId}")
    public ResponseEntity<Map<String, Object>> removeWontWatchAnime(@PathVariable Long userId, @PathVariable Long animeId) {
        log.info("Removing anime {} from user {}'s wont watch list", animeId, userId);
        Set<Long> updatedList = animeService.removeWontWatchAnimeByID(userId, animeId);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Anime ID " + animeId + " has been removed from your wont watch list.");
        response.put("wontWatch", updatedList);
        return ResponseEntity.ok(response);
    }

    // The following 4 HTTP Requests revolve around animeReviews

    // Create a new review
    @PostMapping("user/{userId}/anime/{animeId}/review")
    @ResponseStatus(HttpStatus.CREATED)
    public AnimeReviewData createReview(@PathVariable Long userId, @PathVariable Long animeId,
            @Valid @RequestBody AnimeReviewData animeReviewData) {
        log.info("Creating review: {}", animeReviewData);

        return animeService.saveReview(userId, animeId, animeReviewData);
    }

    // Update a review
    @PutMapping("user/{userId}/anime/{animeId}/review/{reviewId}")
    public AnimeReviewData updateReview(@PathVariable Long userId, @PathVariable Long animeId,
            @PathVariable Long reviewId, @Valid @RequestBody AnimeReviewData animeReviewData) {
        animeReviewData.setReviewId(reviewId);
        animeReviewData.setUserId(userId);
        animeReviewData.setAnimeId(animeId);

        log.info("Updating review: {}", animeReviewData);

        return animeService.saveReview(userId, animeId, animeReviewData);
    }

    // Delete a review by ID
    @DeleteMapping("user/{userId}/anime/{animeId}/review/{reviewId}")
    public Map<String, String> deleteReview(@PathVariable Long userId, @PathVariable Long animeId,
            @PathVariable Long reviewId) {
        log.info("Deleting review: {}", reviewId);
        animeService.deleteReviewById(userId, animeId, reviewId);

        return Map.of("message", "Review ID deleted successfully");
    }
}
