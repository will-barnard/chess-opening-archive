package com.barnard.ChessArchive.controller;

import com.barnard.ChessArchive.dao.CategoryDao;
import com.barnard.ChessArchive.model.OpeningCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/category")
public class CategoryController {

    @Autowired
    private CategoryDao categoryDao;

    @GetMapping(path = "/{categoryId}")
    public OpeningCategory getCategory(@PathVariable int categoryId) {
        return categoryDao.getCategory(categoryId);
    }

    @PostMapping(path = "")
    public OpeningCategory createCategory(@RequestBody OpeningCategory openingCategory) {
        return categoryDao.createCategory(openingCategory);
    }

    @PutMapping(path = "")
    public OpeningCategory updateCategory(@RequestBody OpeningCategory openingCategory) {
        return categoryDao.updateCategory(openingCategory);
    }

    @DeleteMapping(path = "/{categoryId}")
    public void deleteCategory(@PathVariable int categoryId) {
        categoryDao.deleteCategory(categoryId);
    }
}
