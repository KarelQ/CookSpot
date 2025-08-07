package cookspot.com.cookspot.repository;


import cookspot.com.cookspot.entity.Category;
import cookspot.com.cookspot.entity.Post;
import cookspot.com.cookspot.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PostRepository extends JpaRepository<Post, String> {

    List<Post> findByUser(UserInfo user);

    List<Post> findByPostCategoriesList(Set<Category> category);
}