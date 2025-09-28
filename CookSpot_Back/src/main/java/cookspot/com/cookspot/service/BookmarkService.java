package cookspot.com.cookspot.service;

import cookspot.com.cookspot.entity.Bookmark;
import cookspot.com.cookspot.embeddedId.BookmarkId;
import cookspot.com.cookspot.entity.Post;
import cookspot.com.cookspot.repository.BookmarkRepository;
import cookspot.com.cookspot.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BookmarkService {


    private final BookmarkRepository bookmarkRepository;
    private final PostRepository postRepository;

    @Autowired
    public BookmarkService(BookmarkRepository bookmarkRepository,  PostRepository postRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.postRepository = postRepository;
    }


    public List<Post> getBookmarkedPostsByUserId(String userId) {
        List<Bookmark> bookmarks = bookmarkRepository.findByIdIdUser(userId).orElse(null);

        if (bookmarks == null) {
            return null;
        }

        return bookmarks.stream()
                .map(bookmark -> postRepository.findById(bookmark.getId().getIdPost()).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    public boolean isBookmarked(String idUser, String idPost) {
        return bookmarkRepository.existsByIdIdUserAndIdIdPost(idUser, idPost);
    }

    @Transactional
    public boolean updateBookmark(String idUser, String idPost) {
        BookmarkId bookmarkId = new BookmarkId();
        bookmarkId.setIdUser(idUser);
        bookmarkId.setIdPost(idPost);

        if (bookmarkRepository.existsById(bookmarkId)) {
            bookmarkRepository.deleteById(bookmarkId);
            return false; // usunięty
        } else {
            Bookmark bookmark = new Bookmark();
            bookmark.setId(bookmarkId);
            bookmarkRepository.save(bookmark);
            return true; // dodany
        }
    }
}