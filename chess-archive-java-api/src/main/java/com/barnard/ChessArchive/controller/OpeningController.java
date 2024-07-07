package com.barnard.ChessArchive.controller;

import com.barnard.ChessArchive.dao.OpeningDao;
import com.barnard.ChessArchive.model.Opening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/opening")
public class OpeningController {

    @Autowired
    private OpeningDao openingDao;

    @GetMapping(path = "/{openingId}")
    public Opening getOpening(@PathVariable int openingId) {
        return openingDao.getOpening(openingId);
    }

    @GetMapping(path = "")
    public List<Opening> getAllOpenings() {
        System.out.println("Hello");
        List<Opening> openingList = new ArrayList<>();
        try {
            openingList = openingDao.getAllOpenings();
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong");
        }
        return openingList;
    }

    @GetMapping(path = "/category/{categoryId}")
    public List<Opening> getOpeningsByCategory(@PathVariable int categoryId) {
        List<Opening> openingList = new ArrayList<>();
        try {
            openingList = openingDao.getOpeningsByCategory(categoryId);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong");
        }

        return openingList;
    }

    @GetMapping(path = "/search")
    public List<Opening> searchName(@RequestBody String search) {
        return openingDao.searchLikeOpeningName(search);
    }

    @GetMapping(path = "/searchPgn")
    public List<Opening> searchPGN(@RequestBody String pgn) {
        return openingDao.searchLikeOpeningPgn(pgn);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "")
    public Opening createOpening(@RequestBody Opening opening) {
        return openingDao.createOpening(opening);
    }

    @PutMapping(path = "")
    public Opening updateOpening(@RequestBody Opening opening) {
        return openingDao.updateOpening(opening);
    }

    @DeleteMapping(path = "/{openingId}")
    public void deleteOpening(@PathVariable int openingId) {
        openingDao.deleteOpening(openingId);
    }
}
