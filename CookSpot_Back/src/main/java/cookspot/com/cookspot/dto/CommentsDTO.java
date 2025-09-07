package cookspot.com.cookspot.dto;

import java.time.Instant;

public class CommentsDTO {
    private String username;
    private String commentContent;
    private Instant commentDate;

    public CommentsDTO(String username, Instant commentDate, String commentContent ){
        this.username = username;
        this.commentDate = commentDate;
        this.commentContent = commentContent;
    }

    public CommentsDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Instant getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Instant commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }


}
