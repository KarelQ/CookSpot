package cookspot.com.cookspot.repository;

import cookspot.com.cookspot.entity.Bookmark;
import cookspot.com.cookspot.entity.BookmarkId;
import cookspot.com.cookspot.entity.Rating;
import cookspot.com.cookspot.entity.RatingId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, RatingId> {
    Optional<Rating> findByIdIdUserAndIdIdPost(String idUser, String idPost);
    boolean existsByIdIdUserAndIdIdPost(String idUser, String idPost);
}
