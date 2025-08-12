package cookspot.com.cookspot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.ColumnDefault;


@Entity
@Table(name = "users")
public class UserInfo {
    @Id
    @Column(name = "id_user", nullable = false, length = 50)
    private String idUser;

    @ColumnDefault("ROLE_USER")
    @Column(name = "role", nullable = false, length = 15)
    private String role =  "ROLE_USER";

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    public UserInfo(String idUser, String email, String role, String password, String username) {
        this.idUser = idUser;
        this.email = email;
        this.role = role;
        this.password = password;
        this.username = username;
    }

    public UserInfo() {
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}