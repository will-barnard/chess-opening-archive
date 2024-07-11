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

        String sql = "SELECT opening.*, category.*, source_material.source_name " +
                "FROM opening " +
                "LEFT JOIN opening_category ON opening.opening_id = opening_category.opening_id " +
                "LEFT JOIN category ON opening_category.category_id = category.category_id " +
                "LEFT JOIN source_material on opening.source_id = source_material.source_id " +
                "WHERE opening.opening_id = ?;";

        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, openingId);
            while (rowSet.next()) {
                if (opening == null) {
                    opening = mapRowToOpening(rowSet);
                    if (rowSet.getString("category_name") != null) {
                        opening.getCategories().add(mapRowToCategory(rowSet));
                    }
                } else {
                    opening.getCategories().add(mapRowToCategory(rowSet));
                }
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
        String sql = "SELECT opening.*, category.*, source_material.source_name " +
                "FROM opening " +
                "LEFT JOIN opening_category ON opening.opening_id = opening_category.opening_id " +
                "LEFT JOIN category ON opening_category.category_id = category.category_id " +
                "LEFT JOIN source_material on opening.source_id = source_material.source_id;";

        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
            while (rowSet.next()) {
                if (openings.size() == 0) {
                    openings.add(mapRowToOpening(rowSet));
                    if (rowSet.getString("category_name") != null) {
                        openings.get(openings.size() - 1).getCategories().add(mapRowToCategory(rowSet));
                    }
                } else {
                    int openingId = rowSet.getInt("opening_id");
                    if (openings.get(openings.size() - 1).getOpeningId() == openingId) {
                        openings.get(openings.size() - 1).getCategories().add(mapRowToCategory(rowSet));
                    } else {
                        openings.add(mapRowToOpening(rowSet));
                        if (rowSet.getString("category_name") != null) {
                            openings.get(openings.size() - 1).getCategories().add(mapRowToCategory(rowSet));
                        }
                    }
                }
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return openings;
    }

    @Override
    public List<Opening> getOpeningsByCategory(int categoryId) {
        List<Opening> openings = new ArrayList<>();
        String sql = "SELECT opening.opening_id " +
                "FROM opening " +
                "JOIN opening_category on opening.category_id = opening_category.category_id " +
                "WHERE opening_category.category_id = ?;";

        String sql2 = "SELECT opening.*, category.*, source_material.source_name " +
                "FROM opening " +
                "LEFT JOIN opening_category on opening.category_id = opening_category.category_id " +
                "LEFT JOIN category ON opening_category.category_id = category.category_id " +
                "LEFT JOIN source_material ON opening.source_id = source_material.source_id" +
                "WHERE opening_id = ";
        String joinStr = " OR opening_id = ";
        boolean firstLoop = true;
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, categoryId);
            while (rowSet.next()) {
                int id = rowSet.getInt("opening_id");
                if (firstLoop) {
                    sql2 += id;
                    firstLoop = false;
                } else {
                    sql2 += joinStr + id;
                }
            }
            sql2 += ";";
            SqlRowSet rowSet1 = jdbcTemplate.queryForRowSet(sql2);
            while (rowSet1.next()) {
                if (openings.size() == 0) {
                    openings.add(mapRowToOpening(rowSet));
                    if (rowSet.getString("category_name") != null) {
                        openings.get(openings.size() - 1).getCategories().add(mapRowToCategory(rowSet));
                    }
                } else {
                    int openingId = rowSet.getInt("opening_id");
                    if (openings.get(openings.size() - 1).getOpeningId() == openingId) {
                        openings.get(openings.size() - 1).getCategories().add(mapRowToCategory(rowSet));
                    } else {
                        openings.add(mapRowToOpening(rowSet));
                        if (rowSet.getString("category_name") != null) {
                            openings.get(openings.size() - 1).getCategories().add(mapRowToCategory(rowSet));
                        }
                    }
                }
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
        List<Opening> openings = new ArrayList<>();
        search = "%" + search + "%";
        String sql = "SELECT opening.*, source_material.source_name " +
                "FROM opening " +
                "LEFT JOIN opening_category on opening.opening_id = opening_category.opening_id " +
                "LEFT JOIN category ON opening_category.category_id = category.category_id " +
                "LEFT JOIN source_material on opening.source_id = source_material.source_id " +
                "WHERE category.category_name LIKE ?;";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, search);
            while (rowSet.next()) {
                if (openings.size() == 0) {
                    openings.add(mapRowToOpening(rowSet));
                    if (rowSet.getString("category_name") != null) {
                        openings.get(openings.size() - 1).getCategories().add(mapRowToCategory(rowSet));
                    }
                } else {
                    int openingId = rowSet.getInt("opening_id");
                    if (openings.get(openings.size() - 1).getOpeningId() == openingId) {
                        openings.get(openings.size() - 1).getCategories().add(mapRowToCategory(rowSet));
                    } else {
                        openings.add(mapRowToOpening(rowSet));
                        if (rowSet.getString("category_name") != null) {
                            openings.get(openings.size() - 1).getCategories().add(mapRowToCategory(rowSet));
                        }
                    }
                }
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return openings;
    }

    @Override
    public List<Opening> searchLikeOpeningPgn(String pgn) {
        List<Opening> openings = new ArrayList<>();
        pgn = "%" + pgn + "%";
        String sql = "SELECT opening.*, source_material.source_name " +
                "FROM opening " +
                "LEFT JOIN opening_category on opening.opening_id = opening_category.opening_id " +
                "LEFT JOIN category ON opening_category.category_id = category.category_id " +
                "LEFT JOIN source_material on opening.source_id = source_material.source_id " +
                "WHERE category.category_name LIKE ?;";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, pgn);
            while (rowSet.next()) {
                if (openings.size() == 0) {
                    openings.add(mapRowToOpening(rowSet));
                    if (rowSet.getString("category_name") != null) {
                        openings.get(openings.size() - 1).getCategories().add(mapRowToCategory(rowSet));
                    }
                } else {
                    int openingId = rowSet.getInt("opening_id");
                    if (openings.get(openings.size() - 1).getOpeningId() == openingId) {
                        openings.get(openings.size() - 1).getCategories().add(mapRowToCategory(rowSet));
                    } else {
                        openings.add(mapRowToOpening(rowSet));
                        if (rowSet.getString("category_name") != null) {
                            openings.get(openings.size() - 1).getCategories().add(mapRowToCategory(rowSet));
                        }
                    }
                }
            }
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
        String sql = "INSERT INTO opening (opening_name, source_id, source_page, source_subnumber, pgn, notes) " +
                "VALUES (?, ?, ?, ?, ?, ?) " +
                "RETURNING opening_id;";
        String sql2 = "";

        try {
            int openingId = jdbcTemplate.queryForObject(sql, int.class, opening.getOpeningName(), opening.getSource().getSourceId(),
                    opening.getSource().getSourcePage(), opening.getSource().getSubnumber(), opening.getPgn(), opening.getNotes());
            if (opening.getCategories().size() > 0) {
                for (OpeningCategory category : opening.getCategories()) {
                    String temp = "INSERT INTO opening_category (opening_id, category_id) VALUES (";
                    temp += openingId + ", " + category.getCategoryId() + ");";
                    sql2 += temp;
                }
                jdbcTemplate.update(sql2);
            }
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
                "SET opening_name = ?, source_id = ?, source_page = ?, source_subnumber = ?, pgn = ?, notes = ? " +
                "WHERE opening_id = ?; " +
                "DELETE FROM opening_category " +
                "WHERE opening_id = ?;";
        String sql2 = "";
        for (OpeningCategory category : opening.getCategories()) {
            String temp = "INSERT INTO opening_category (opening_id, category_id) VALUES (";
            temp += opening.getOpeningId() + ", " + category.getCategoryId() + ");";
            sql2 += temp;
        }

        try {
            int rowsAffected = jdbcTemplate.update(sql, opening.getOpeningName(), opening.getSource().getSourceId(),
                    opening.getSource().getSourcePage(), opening.getSource().getSubnumber(), opening.getPgn(), opening.getNotes(),
                    opening.getOpeningId(), opening.getOpeningId());
            if (rowsAffected == 0) {
                throw new DaoException();
            }
            if (opening.getCategories().size() > 0) {
                jdbcTemplate.update(sql2);
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
        String sql = "DELETE FROM opening_category " +
                "WHERE opening_id = ?; " +
                "DELETE FROM opening " +
                "WHERE opening_id = ?;";
        try {
            jdbcTemplate.update(sql, openingId, openingId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    private Opening mapRowToOpening(SqlRowSet rs) {
        Opening opening = new Opening();
        Source source = new Source();
        opening.setCategories(new ArrayList<>());

        opening.setOpeningId(rs.getInt("opening_id"));
        opening.setOpeningName(rs.getString("opening_name"));
        if (rs.getInt("source_id") != 0) {
            int sourceId = rs.getInt("source_id");
            source.setSourceId(sourceId);
            source.setSourceName(rs.getString("source_name"));
            source.setSourcePage(rs.getInt("source_page"));
            source.setSubnumber(rs.getInt("source_subnumber"));
        }
        opening.setPgn(rs.getString("pgn"));
        opening.setNotes(rs.getString("notes"));
        opening.setSource(source);

        return opening;
    }

    private OpeningCategory mapRowToCategory(SqlRowSet rs) {
        OpeningCategory category = new OpeningCategory();

        category.setCategoryId(rs.getInt("category_id"));
        category.setCategoryName(rs.getString("category_name"));

        return category;
    }
}
