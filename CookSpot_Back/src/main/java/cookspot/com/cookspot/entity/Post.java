package cookspot.com.cookspot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Set;


@Entity
@Table(name = "posts")
public class Post {
    @Id
    @Column(name = "id_post", nullable = false, length = 50)
    private String idPost;

//    @Column(name = "id_user_owner", nullable = false)
//    private String idUserOwner;k

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false, length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "ingredients", nullable = false, length = Integer.MAX_VALUE)
    private String ingredients;

    @Column(name = "recipe", nullable = false, length = Integer.MAX_VALUE)
    private String recipe;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "prep_time", nullable = false, length = 10)
    private String prepTime;

    @Column(name = "difficulty", nullable = false, length = 10)
    private String difficulty;

    @Column(name = "number_of_servings", nullable = false)
    private Integer numberOfServings;

    @Column(name = "created_at", nullable = false, length = 20)
    private String createdAt;

    @ColumnDefault("0")
    @Column(name = "likes", nullable = false)
    private Integer like;

    @ColumnDefault("0")
    @Column(name = "dislike", nullable = false)
    private Integer dislike;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_user_owner")
    private UserInfo user;

    @ManyToMany
    @JoinTable(
            name = "post_categories",
            joinColumns = @JoinColumn(name = "id_post"),
            inverseJoinColumns = @JoinColumn(name = "id_category"))
    Set<Category> postCategoriesList;



//    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
//    private List<PostCategory> postCategories = new ArrayList<>();

//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
//    private String username;

//    @Transient
//    private String username; // Pole pochodzące z tabeli `users`


    public Post(String idPost, String title, String description, String ingredients, String recipe, String image, String prepTime, String difficulty, Integer numberOfServings, String createdAt, Integer like, Integer dislike, UserInfo user, Set<Category> postCategoriesList) {
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
        this.user = user;
        this.postCategoriesList = postCategoriesList;
    }

    public Post() {
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

    public String getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(String prepTime) {
        this.prepTime = prepTime;
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

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public Integer getDislike() {
        return dislike;
    }

    public void setDislike(Integer dislike) {
        this.dislike = dislike;
    }

    public Set<Category> getPostCategoriesList() {
        return postCategoriesList;
    }

    public void setPostCategoriesList(Set<Category> postCategoriesList) {
        this.postCategoriesList = postCategoriesList;
    }

    @Override
    public String toString() {
        return "Post{" +
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
                ", user=" + user +
                ", postCategoriesList=" + postCategoriesList +
                '}';
    }
}