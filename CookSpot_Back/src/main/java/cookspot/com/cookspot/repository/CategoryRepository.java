package cookspot.com.cookspot.repository;


import cookspot.com.cookspot.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {

}
