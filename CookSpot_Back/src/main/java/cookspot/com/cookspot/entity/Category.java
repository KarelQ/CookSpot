package cookspot.com.cookspot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.util.Set;



@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categories_id_gen")
    @SequenceGenerator(name = "categories_id_gen", sequenceName = "categories_id_category_seq", allocationSize = 1)
    @Column(name = "id_category", nullable = false)
    private Integer id;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_desc")
    private String categoryDesc;

    @JsonIgnore
    @ManyToMany(mappedBy = "postCategoriesList")
    Set<Post> postCategory;

//    public Category getCategory() {
//        return this;
//    }

//    @ManyToMany
//    @JoinTable(name = "post_categories",
//            joinColumns = @JoinColumn(name = "id_category"),
//            inverseJoinColumns = @JoinColumn(name = "id_post"))
//    private Set<Post> posts = new LinkedHashSet<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Post> getPostCategory() {
        return postCategory;
    }

    public void setPostCategory(Set<Post> postCategory) {
        this.postCategory = postCategory;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }
}