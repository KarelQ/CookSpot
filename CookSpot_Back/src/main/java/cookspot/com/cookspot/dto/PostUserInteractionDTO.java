package cookspot.com.cookspot.dto;

public class PostUserInteractionDTO {
    private String idUser;
    private String idPost;
    private int isLiked;
    private boolean isBooked;
    private boolean isOwner;

    public PostUserInteractionDTO(String idUser, boolean isOwner, boolean isBooked, int isLiked, String idPost) {
        this.idUser = idUser;
        this.isOwner = isOwner;
        this.isBooked = isBooked;
        this.isLiked = isLiked;
        this.idPost = idPost;
    }

    public PostUserInteractionDTO() {}

    public int getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(int isLiked) {
        this.isLiked = isLiked;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
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
}
