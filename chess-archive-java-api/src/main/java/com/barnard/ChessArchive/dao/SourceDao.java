package com.barnard.ChessArchive.dao;

import com.barnard.ChessArchive.model.Source;

import java.util.List;

public interface SourceDao {

    Source getSource(int sourceId);
    List<Source> getAllSources();
    Source createSource(Source source);
    Source updateSource(Source source);
    void deleteSource(int sourceId);
}
