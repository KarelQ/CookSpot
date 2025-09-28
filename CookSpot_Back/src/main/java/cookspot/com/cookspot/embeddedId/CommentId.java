package cookspot.com.cookspot.embeddedId;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CommentId implements Serializable {
    private static final long serialVersionUID = 5887669302310474433L;
    @Column(name = "id_post", nullable = false, length = 50)
    private String idPost;

    @Column(name = "id_user", nullable = false, length = 50)
    private String idUser;

    public CommentId(String idUser, String idPost) {
        this.idUser = idUser;
        this.idPost = idPost;
    }

    public CommentId() {

    }

    public String getIdPost() {
        return idPost;
    }

    public void setIdPost(String idPost) {
        this.idPost = idPost;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CommentId entity = (CommentId) o;
        return Objects.equals(this.idUser, entity.idUser) &&
                Objects.equals(this.idPost, entity.idPost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idPost);
    }

}