package com.barnard.chess_archive_java_api.controller;

import com.barnard.chess_archive_java_api.dao.OpeningDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class OpeningController {

    @Autowired
    private OpeningDao openingDao;


}
