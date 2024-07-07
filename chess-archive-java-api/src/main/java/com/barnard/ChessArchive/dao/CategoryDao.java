package com.barnard.ChessArchive.dao;

import com.barnard.ChessArchive.model.OpeningCategory;

public interface CategoryDao {

    OpeningCategory getCategory(int categoryId);
    OpeningCategory createCategory(OpeningCategory category);
    OpeningCategory updateCategory(OpeningCategory category);
    void deleteCategory(int categoryId);
}
