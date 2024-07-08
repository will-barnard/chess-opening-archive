package com.barnard.ChessArchive.dao;

import com.barnard.ChessArchive.exception.DaoException;
import com.barnard.ChessArchive.model.Source;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcSourceDao implements SourceDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Source getSource(int sourceId) {
        Source source = new Source();
        String sql = "SELECT * FROM source_material " +
                "WHERE source_id = ?;";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, sourceId);
            if (rowSet.next()) {
                source.setSourceId(sourceId);
                source.setSourceName(rowSet.getString("source_name"));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return source;
    }

    @Override
    public List<Source> getAllSources() {
        List<Source> sources = new ArrayList<>();
        String sql = "SELECT * FROM source_material";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
            if (rowSet.next()) {
                sources.add(new Source(rowSet.getInt("source_id"), rowSet.getString("source_name")));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return sources;
    }

    @Override
    public Source createSource(Source source) {
        Source newSource = null;
        String sql = "INSERT INTO source_material (source_name) " +
                "VALUES (?) " +
                "RETURNING source_id;";
        try {
            int sourceId = jdbcTemplate.queryForObject(sql, int.class, source.getSourceName());
            newSource = getSource(sourceId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newSource;
    }

    @Override
    public Source updateSource(Source source) {
        Source newSource = new Source();
        String sql = "UPDATE source_material " +
                "SET source_name = ? " +
                "WHERE source_id = ?;";
        try {
            jdbcTemplate.update(sql, source.getSourceName(), source.getSourceId());
            newSource = getSource(source.getSourceId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newSource;
    }

    @Override
    public void deleteSource(int sourceId) {
        String sql = "DELETE FROM source_material " +
                "WHERE source_id = ?;";
        try {
            int rowsAffected = jdbcTemplate.update(sql, sourceId);
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
