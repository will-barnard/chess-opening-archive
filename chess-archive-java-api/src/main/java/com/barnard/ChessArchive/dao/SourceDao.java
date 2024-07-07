package com.barnard.ChessArchive.dao;

import com.barnard.ChessArchive.model.Source;

public interface SourceDao {

    Source getSource(int sourceId);
    Source createSource(Source source);
    Source updateSource(Source source);
    void deleteSource(int sourceId);
}
