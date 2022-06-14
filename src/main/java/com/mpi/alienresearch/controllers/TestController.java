package com.mpi.alienresearch.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping
    public Object getTestMessage() {
        return new Object(){
            public final String text = "Test massage from json object";
        };
    }

}
