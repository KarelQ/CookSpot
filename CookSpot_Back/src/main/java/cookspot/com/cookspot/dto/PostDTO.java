package cookspot.com.cookspot.dto;


import jakarta.persistence.Entity;

import java.util.List;
import java.util.Set;


public class PostDTO {
    private String idPost;
    private String title;
    private String description;
    private String ingredients;
    private String recipe;
    private String image;
    private String prepTime;
    private String difficulty;
    private Integer numberOfServings;
    private String createdAt;
    private Integer like;
    private Integer dislike;
    private String username;
    private String idUser;
    private Float starRating;
    private Set<String> categoryNames;

    public PostDTO(String idPost, String title, String description, String ingredients, String recipe, String image, String prepTime, String difficulty, Integer numberOfServings, String createdAt, Integer like, Integer dislike, String username, String idUser, Set<String> categoryNames, Float starRating ) {
        this.idPost = idPost;
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.recipe = recipe;
        this.image = image;
        this.prepTime = prepTime;
        this.difficulty = difficulty;
        this.numberOfServings = numberOfServings;
        this.createdAt = createdAt;
        this.like = like;
        this.dislike = dislike;
        this.username = username;
        this.idUser = idUser;
        this.categoryNames = categoryNames;
        this.starRating = starRating;
    }

    public PostDTO() {
    }

    public String getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(String prepTime) {
        this.prepTime = prepTime;
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

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getNumberOfServings() {
        return numberOfServings;
    }

    public void setNumberOfServings(Integer numberOfServings) {
        this.numberOfServings = numberOfServings;
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

    public Set<String> getCategoryNames() {
        return categoryNames;
    }

    public void setCategoryNames(Set<String> categoryNames) {
        this.categoryNames = categoryNames;
    }

    public Float getStarRating() {
        return starRating;
    }

    public void setStarRating(Float starRating) {
        this.starRating = starRating;
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
                ", prepTime='" + prepTime + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", numberOfServings=" + numberOfServings +
                ", createdAt='" + createdAt + '\'' +
                ", like=" + like +
                ", dislike=" + dislike +
                ", username='" + username + '\'' +
                ", idUser='" + idUser + '\'' +
                ", categoryNames=" + categoryNames +
                '}';
    }
}