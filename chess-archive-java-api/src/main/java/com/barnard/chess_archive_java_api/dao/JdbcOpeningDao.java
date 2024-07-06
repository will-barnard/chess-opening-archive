package com.barnard.chess_archive_java_api.dao;

import com.barnard.chess_archive_java_api.model.Opening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcOpeningDao implements OpeningDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Opening getOpening(int openingId) {
        return null;
    }

    @Override
    public List<Opening> getAllOpenings() {
        return null;
    }

    @Override
    public List<Opening> searchLikeOpeningName(String search) {
        return null;
    }

    @Override
    public List<Opening> searchLikeOpeningPgn(String pgn) {
        return null;
    }

    @Override
    public Opening createOpening(Opening opening) {
        return null;
    }

    @Override
    public Opening updateOpening(Opening opening) {
        return null;
    }

    @Override
    public void deleteOpening(int openingId) {

    }
}
