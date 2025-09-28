package cookspot.com.cookspot.entity;

import cookspot.com.cookspot.embeddedId.BookmarkId;
import jakarta.persistence.*;


@Entity
@Table(name = "bookmarks")
public class Bookmark {
    @EmbeddedId
    private BookmarkId id;

    public BookmarkId getId() {
        return id;
    }

    public void setId(BookmarkId id) {
        this.id = id;
    }
}