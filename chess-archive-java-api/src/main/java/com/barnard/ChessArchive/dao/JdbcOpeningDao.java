package com.barnard.ChessArchive.dao;

import com.barnard.ChessArchive.exception.DaoException;
import com.barnard.ChessArchive.model.Opening;
import com.barnard.ChessArchive.model.OpeningCategory;
import com.barnard.ChessArchive.model.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcOpeningDao implements OpeningDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Opening getOpening(int openingId) {
        Opening opening = null;
        String sql = "SELECT opening_id, opening.opening_id, opening.source_id, source_page, source_subnumber, pgn, notes, opening_category.category_name, source_material.source_name " +
                "FROM opening " +
                "JOIN opening_category on opening.category_id = opening_category.category_id " +
                "JOIN source_material on opening.source_id = source_material.source_id " +
                "WHERE opening_id = ?;";

        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, openingId);
            if (rowSet.next()) {
                opening = mapRowToOpening(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return opening;
    }

    @Override
    public List<Opening> getAllOpenings() {
        List<Opening> openings = new ArrayList<>();
        String sql = "SELECT opening_id, opening.opening_id, opening.source_id, source_page, source_subnumber, pgn, notes, opening_category.category_name, source_material.source_name " +
                "FROM opening " +
                "JOIN opening_category on opening.category_id = opening_category.category_id " +
                "JOIN source_material on opening.source_id = source_material.source_id;";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
            if (rowSet.next()) {
                openings.add(mapRowToOpening(rowSet));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return openings;
    }

    @Override
    public List<Opening> searchLikeOpeningName(String search) {
        // todo
        try {

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return null;
    }

    @Override
    public List<Opening> searchLikeOpeningPgn(String pgn) {
        // todo
        try {

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return null;
    }

    @Override
    public Opening createOpening(Opening opening) {
        Opening newOpening;
        String sql = "INSERT INTO opening (category_id, source_id, source_page, source_subnumber, pgn, notes) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            int openingId = jdbcTemplate.queryForObject(sql, int.class, opening.getOpeningCategory().getCategoryId(), opening.getSource().getSourceId(),
                    opening.getSource().getSourcePage(), opening.getSource().getSubnumber(), opening.getPgn(), opening.getNotes());
            newOpening = getOpening(openingId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newOpening;
    }

    @Override
    public Opening updateOpening(Opening opening) {
        Opening updatedOpening = null;
        String sql = "UPDATE opening " +
                "SET category_id = ?, source_id = ?, source_page = ?, source_subnumber = ?, pgn = ?, notes = ? " +
                "WHERE opening_id = ?;";
        try {
            int rowsAffected = jdbcTemplate.update(sql, opening.getOpeningCategory().getCategoryId(), opening.getSource().getSourceId(),
                    opening.getSource().getSourcePage(), opening.getSource().getSubnumber(), opening.getPgn(), opening.getNotes(),
                    opening.getOpeningId());
            if (rowsAffected == 0) {
                throw new DaoException();
            }
            updatedOpening = getOpening(opening.getOpeningId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedOpening;
    }

    @Override
    public void deleteOpening(int openingId) {
        String sql = "DELETE FROM opening " +
                "WHERE opening_id = ?;";
        try {
            int rowsAffected = jdbcTemplate.update(sql, openingId);
            if (rowsAffected == 0) {
                throw new DaoException();
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    private Opening mapRowToOpening(SqlRowSet rs) {
        Opening opening = new Opening();
        OpeningCategory openingCategory = new OpeningCategory();
        Source source = new Source();

        opening.setOpeningId(rs.getInt("opening_id"));

        int catId = rs.getInt("category_id");
        openingCategory.setCategoryId(catId);
        openingCategory.setCategoryName(rs.getString("category_name"));

        int sourceId = rs.getInt("source_id");
        source.setSourceId(sourceId);
        source.setSourcePage(rs.getInt("source_page"));
        source.setSubnumber(rs.getInt("source_subnumber"));


        opening.setPgn(rs.getString("pgn"));
        opening.setNotes(rs.getString("notes"));

        opening.setOpeningCategory(openingCategory);
        opening.setSource(source);
        return opening;
    }
}
