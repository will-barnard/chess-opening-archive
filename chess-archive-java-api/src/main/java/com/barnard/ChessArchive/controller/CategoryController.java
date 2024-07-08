package com.barnard.ChessArchive.controller;

import com.barnard.ChessArchive.dao.CategoryDao;
import com.barnard.ChessArchive.model.OpeningCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(path = "")
    public List<OpeningCategory> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "")
    public OpeningCategory createCategory(@RequestBody OpeningCategory openingCategory) {
        return categoryDao.createCategory(openingCategory);
    }

    @PutMapping(path = "")
    public OpeningCategory updateCategory(@RequestBody OpeningCategory openingCategory) {
        return categoryDao.updateCategory(openingCategory);
    }

    @PostMapping(path = "/{categoryId}/{openingId}")
    public void addCategoryToOpening(@PathVariable int categoryId, @PathVariable int openingId) {
        categoryDao.addCategoryToOpening(categoryId, openingId);
    }

    @DeleteMapping(path = "/{categoryId}/{openingId}")
    public void removeCategoryFromOpening(@PathVariable int categoryId, @PathVariable int openingId) {
        categoryDao.removeCategoryFromOpening(categoryId, openingId);
    }

    @DeleteMapping(path = "/{categoryId}")
    public void deleteCategory(@PathVariable int categoryId) {
        categoryDao.deleteCategory(categoryId);
    }
}
