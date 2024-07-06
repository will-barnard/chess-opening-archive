package com.barnard.chess_archive_java_api.dao;

import com.barnard.chess_archive_java_api.model.Opening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface OpeningDao {
    Opening getOpening(int openingId);
    List<Opening> getAllOpenings();
    List<Opening> searchLikeOpeningName(String search);
    List<Opening> searchLikeOpeningPgn(String pgn);
    Opening createOpening(Opening opening);
    Opening updateOpening(Opening opening);
    void deleteOpening(int openingId);
}
