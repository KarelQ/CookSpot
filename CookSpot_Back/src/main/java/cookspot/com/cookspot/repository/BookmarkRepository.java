package cookspot.com.cookspot.repository;

import cookspot.com.cookspot.entity.Bookmark;
import cookspot.com.cookspot.entity.BookmarkId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, BookmarkId> {
    boolean existsByIdIdUserAndIdIdPost(String idUser, String idPost);
}
