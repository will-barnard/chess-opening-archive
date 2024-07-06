package com.barnard.ChessArchive.controller;

import com.barnard.ChessArchive.dao.OpeningDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class OpeningController {

    @Autowired
    private OpeningDao openingDao;


}
