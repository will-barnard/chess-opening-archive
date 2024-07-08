package com.barnard.ChessArchive.controller;

import com.barnard.ChessArchive.dao.SourceDao;
import com.barnard.ChessArchive.model.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/source")
public class SourceController {

    @Autowired
    private SourceDao sourceDao;

    @GetMapping(path = "/{sourceId}")
    public Source getSource(@PathVariable int sourceId) {
        return sourceDao.getSource(sourceId);
    }

    @GetMapping(path = "")
    public List<Source> getAllSources() {
        return sourceDao.getAllSources();
    }

    @PostMapping(path = "")
    public Source createSource(@RequestBody Source source) {
        return sourceDao.createSource(source);
    }

    @PutMapping(path = "")
    public Source updateSource(@RequestBody Source source) {
        return sourceDao.updateSource(source);
    }

    @DeleteMapping(path = "/{sourceId}")
    public void deleteSource(@PathVariable int sourceId) {
        sourceDao.deleteSource(sourceId);
    }
}
