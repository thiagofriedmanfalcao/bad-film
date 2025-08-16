package com.badfilm;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    private static final String READY_MESSAGE = "Ready...";

    @GetMapping
    public ResponseEntity<?> test() {
        return ResponseEntity.ok(READY_MESSAGE);
    }
}