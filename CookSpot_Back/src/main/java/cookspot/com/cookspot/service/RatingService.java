package cookspot.com.cookspot.service;

import cookspot.com.cookspot.entity.Rating;
import cookspot.com.cookspot.entity.RatingId;
import cookspot.com.cookspot.repository.BookmarkRepository;
import cookspot.com.cookspot.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RatingService {

    private RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }


    public Rating getRatingByUserAndPost(String userId, String postId) {
        return ratingRepository.findByIdIdUserAndIdIdPost(userId, postId).orElse(null);
    }

    public int getStarsFromIdUserAndIdPost(String userId, String postId) {
        Rating rating = getRatingByUserAndPost(userId, postId);
        if (rating == null || rating.getStars() == null) {
            return 0; // brak oceny = 0
        }
        return rating.getStars();

    }

    public int getVotesFromIdUserAndIdPost(String userId, String postId) {
        Rating rating = getRatingByUserAndPost(userId, postId);
        if (rating == null || rating.getVote() == null) {
            return 0; // brak oceny = 0
        }
        return rating.getVote();
    }

    @Transactional
    public Rating setOrUpdateStars(String userId, String postId, int stars) {
        if (stars < 0 || stars > 5) {
            throw new IllegalArgumentException("Stars must be between 0 and 5");
        }

        Rating rating = ratingRepository
                .findByIdIdUserAndIdIdPost(userId, postId)
                .orElseGet(() -> {
                    Rating r = new Rating();
                    r.setId(new RatingId(userId, postId));
                    return r;
                });

        rating.setStars(stars);
        return ratingRepository.save(rating);
    }

    @Transactional
    public Rating setOrUpdateVote(String userId, String postId, int vote) {
        if (vote != -1 && vote != 0 && vote != 1) {
            throw new IllegalArgumentException("Vote must be -1, 0 or 1");
        }

        Rating rating = ratingRepository
                .findByIdIdUserAndIdIdPost(userId, postId)
                .orElseGet(() -> {
                    Rating r = new Rating();
                    r.setId(new RatingId(userId, postId));
                    return r;
                });

        rating.setVote(vote);
        return ratingRepository.save(rating);
    }

}
