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
        return null;
    }

    @GetMapping(path = "/category/{categoryId}")
    public List<Opening> getOpeningCategory(@PathVariable int categoryId) {
        List<Opening> openingList = new ArrayList<>();
        try {
            openingList = openingDao.getOpeningsByCategory(categoryId);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong");
        }

        return openingList;
    }

    @GetMapping(path = "")
    public List<Opening> getAllOpenings() {
        List<Opening> openingList = new ArrayList<>();
        try {
            openingList = openingDao.getAllOpenings();
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong");
        }
        return openingList;
    }


}
