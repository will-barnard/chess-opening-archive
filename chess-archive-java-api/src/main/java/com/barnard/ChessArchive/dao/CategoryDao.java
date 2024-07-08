package com.barnard.ChessArchive.dao;

import com.barnard.ChessArchive.model.OpeningCategory;

import java.util.List;

public interface CategoryDao {

    OpeningCategory getCategory(int categoryId);
    List<OpeningCategory> getAllCategories();
    OpeningCategory createCategory(OpeningCategory category);
    OpeningCategory updateCategory(OpeningCategory category);
    void addCategoryToOpening(int categoryId, int openingId);
    void removeCategoryFromOpening(int categoryId, int openingId);
    void deleteCategory(int categoryId);
}
