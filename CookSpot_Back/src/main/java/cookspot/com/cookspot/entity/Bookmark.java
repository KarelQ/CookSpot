package cookspot.com.cookspot.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "bookmarks")
public class Bookmark {
    @EmbeddedId
    private BookmarkId id;

    //TODO [Reverse Engineering] generate columns from DB

//    @ManyToOne
//    @MapsId("idUser") // wskazuje na pole w BookmarkId
//    @JoinColumn(name = "id_user")
//    private UserInfo user;
//
//    @ManyToOne
//    @MapsId("idPost")
//    @JoinColumn(name = "id_post")
//    private Post post;

    public BookmarkId getId() {
        return id;
    }

    public void setId(BookmarkId id) {
        this.id = id;
    }
}