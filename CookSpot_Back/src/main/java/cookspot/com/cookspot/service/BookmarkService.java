package cookspot.com.cookspot.service;

import cookspot.com.cookspot.entity.Bookmark;
import cookspot.com.cookspot.entity.BookmarkId;
import cookspot.com.cookspot.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookmarkService {

    @Autowired
    private final BookmarkRepository bookmarkRepository;

    public BookmarkService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
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