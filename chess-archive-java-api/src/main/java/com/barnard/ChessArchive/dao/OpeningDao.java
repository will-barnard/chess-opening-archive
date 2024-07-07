package com.barnard.ChessArchive.dao;

import com.barnard.ChessArchive.model.Opening;

import java.util.List;

public interface OpeningDao {
    Opening getOpening(int openingId);
    List<Opening> getAllOpenings();
    List<Opening> getOpeningsByCategory(int categoryId);
    List<Opening> searchLikeOpeningName(String search);
    List<Opening> searchLikeOpeningPgn(String pgn);
    Opening createOpening(Opening opening);
    Opening updateOpening(Opening opening);
    void deleteOpening(int openingId);
}
