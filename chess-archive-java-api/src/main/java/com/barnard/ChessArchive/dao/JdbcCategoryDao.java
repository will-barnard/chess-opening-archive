package com.barnard.ChessArchive.dao;

import com.barnard.ChessArchive.exception.DaoException;
import com.barnard.ChessArchive.model.OpeningCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcCategoryDao implements CategoryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public OpeningCategory getCategory(int categoryId) {
        OpeningCategory category = new OpeningCategory();
        String sql = "SELECT * FROM category " +
                "WHERE category_id = ?;";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, categoryId);
            if (rowSet.next()) {
                category.setCategoryId(categoryId);
                category.setCategoryName(rowSet.getString("category_name"));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return category;
    }

    @Override
    public OpeningCategory createCategory(OpeningCategory category) {
        OpeningCategory newCategory = null;
        String sql = "INSERT INTO category (category_name) " +
                "VALUES (?) " +
                "RETURNING category_id;";
        try {
            int categoryId = jdbcTemplate.queryForObject(sql, int.class, category.getCategoryName());
            newCategory = getCategory(categoryId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newCategory;
    }

    @Override
    public OpeningCategory updateCategory(OpeningCategory category) {
        OpeningCategory newCategory = null;
        String sql = "UPDATE category " +
                "SET category_name = ? " +
                "WHERE category_id = ?";
        try {
            jdbcTemplate.update(sql, category.getCategoryName(), category.getCategoryId());
            newCategory = getCategory(category.getCategoryId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newCategory;
    }

    @Override
    public void addCategoryToOpening(int categoryId, int openingId) {
        String sql = "INSERT INTO opening_category (category_id, opening_id) " +
                "VALUES (?, ?);";
        try {
            jdbcTemplate.update(sql, categoryId, openingId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public void removeCategoryFromOpening(int categoryId, int openingId) {
        String sql = "DELETE FROM opening_category " +
                "WHERE category_id = ? " +
                "AND opening_id = ?;";
        try {
            jdbcTemplate.update(sql, categoryId, openingId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public void deleteCategory(int categoryId) {
        String sql = "DELETE FROM opening_category " +
                "WHERE category_id = ?; " +
                "DELETE FROM category " +
                "WHERE category_id = ?;";
        try {
            int rowsAffected = jdbcTemplate.update(sql, categoryId, categoryId);
            if (rowsAffected == 0) {
                throw new DaoException("no rows affected");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }
}
