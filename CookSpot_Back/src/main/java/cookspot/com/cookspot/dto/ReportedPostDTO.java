package cookspot.com.cookspot.dto;


public class ReportedPostDTO {
    private String idPost;
    private String title;
    private String description;
    private String ingredients;
    private String recipe;
    private String image;
    private String createdAt;
    private Integer like;
    private Integer dislike;
    private String username;
    private String idUser;
    private Integer numberOfReports;


    public ReportedPostDTO(String idPost, String title, String description, String ingredients, String recipe, String image, String createdAt, Integer like, Integer dislike, String username, String idUser, Integer numberOfReports) {
        this.idPost = idPost;
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.recipe = recipe;
        this.image = image;
        this.createdAt = createdAt;
        this.like = like;
        this.dislike = dislike;
        this.username = username;
        this.idUser = idUser;
        this.numberOfReports = numberOfReports;
    }

    public ReportedPostDTO() {
    }

public Integer getNumberOfReports() {
        return numberOfReports;
}

    public void setNumberOfReports(Integer numberOfReports) {
        this.numberOfReports = numberOfReports;
    }

    public String getIdPost() {
        return idPost;
    }

    public void setIdPost(String idPost) {
        this.idPost = idPost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Integer getDislike() {
        return dislike;
    }

    public void setDislike(Integer dislike) {
        this.dislike = dislike;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }


    @Override
    public String toString() {
        return "PostDTO{" +
                "idPost='" + idPost + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", recipe='" + recipe + '\'' +
                ", image='" + image + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", like=" + like +
                ", dislike=" + dislike +
                ", username='" + username + '\'' +
                ", idUser='" + idUser + '\'' +
                '}';
    }
}