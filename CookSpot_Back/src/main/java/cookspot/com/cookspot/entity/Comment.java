package cookspot.com.cookspot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "comments")
public class Comment {
    @EmbeddedId
    private CommentId id;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "publication_date", nullable = false)
    private Instant publicationDate;

    @Column(name = "comment_content", nullable = false, length = Integer.MAX_VALUE)
    private String commentContent;

    public CommentId getId() {
        return id;
    }

    public void setId(CommentId id) {
        this.id = id;
    }

    public Instant getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Instant publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

}