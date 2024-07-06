package com.barnard.ChessArchive.dao;

import com.barnard.ChessArchive.model.Source;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcSourceDao implements SourceDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Source createSource() {
        return null;
    }

    @Override
    public Source updateSource() {
        return null;
    }

    @Override
    public void deleteSource() {

    }
}
