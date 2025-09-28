package cookspot.com.cookspot.embeddedId;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ReportedPostId implements Serializable {
    private static final long serialVersionUID = 3754869301830754740L;
    @Column(name = "id_post", nullable = false, length = 50)
    private String idPost;

    @Column(name = "id_user", nullable = false, length = 50)
    private String idUser;

    public ReportedPostId(String idPost, String idUser) {
        this.idPost = idPost;
        this.idUser = idUser;
    }

    public ReportedPostId() {
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
        ReportedPostId entity = (ReportedPostId) o;
        return Objects.equals(this.idUser, entity.idUser) &&
                Objects.equals(this.idPost, entity.idPost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idPost);
    }

}