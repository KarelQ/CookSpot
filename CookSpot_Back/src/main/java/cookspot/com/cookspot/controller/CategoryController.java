package cookspot.com.cookspot.controller;



import cookspot.com.cookspot.entity.Category;
import cookspot.com.cookspot.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/auth/category/names")
    public List<String> getAllPostsDTO() {
        return categoryService.getAllCategoriesNames();
    }



    @GetMapping(("/auth/category"))
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
}