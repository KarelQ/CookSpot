package cookspot.com.cookspot.repository;

import cookspot.com.cookspot.entity.Bookmark;
import cookspot.com.cookspot.embeddedId.BookmarkId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, BookmarkId> {
    boolean existsByIdIdUserAndIdIdPost(String idUser, String idPost);

    Optional<List<Bookmark>> findByIdIdUser(String userId);
}
