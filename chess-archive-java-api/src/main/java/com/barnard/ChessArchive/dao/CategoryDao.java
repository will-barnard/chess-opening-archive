package com.barnard.ChessArchive.dao;

import com.barnard.ChessArchive.model.OpeningCategory;

public interface CategoryDao {

    OpeningCategory createCategory(OpeningCategory category);
    OpeningCategory updateCategory(OpeningCategory category);
    void deleteCategory(int categoryId);
}
