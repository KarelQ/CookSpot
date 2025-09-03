package cookspot.com.cookspot.service;


import cookspot.com.cookspot.entity.Category;
import cookspot.com.cookspot.repository.CategoryRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<String> getAllCategoriesNames() {
        return categoryRepository.findAll()
                .stream()
                .map(Category::getCategoryName)
                .collect(Collectors.toList());
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();

    }



}





