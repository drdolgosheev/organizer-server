package com.hse.organizer.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller just to check if server is working correctly.
 *
 * @author Dolgosheev Dmitriy
 * @version 1.0
 */

@RestController
@RequestMapping
public class Test {
    @GetMapping("/test")
    public String testServer() {
        return "Server OK ";
    }
}
