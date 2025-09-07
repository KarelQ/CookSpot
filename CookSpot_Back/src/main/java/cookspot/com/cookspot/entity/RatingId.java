package cookspot.com.cookspot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RatingId implements Serializable {
    private static final long serialVersionUID = -6842212666629371413L;
    @Column(name = "id_user", nullable = false, length = 50)
    private String idUser;

    @Column(name = "id_post", nullable = false, length = 50)
    private String idPost;

    public RatingId(String userId, String postId) {
        this.idUser = userId;
        this.idPost = postId;
    }

    public RatingId() {

    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdPost() {
        return idPost;
    }

    public void setIdPost(String idPost) {
        this.idPost = idPost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RatingId entity = (RatingId) o;
        return Objects.equals(this.idUser, entity.idUser) &&
                Objects.equals(this.idPost, entity.idPost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idPost);
    }

}