package com.kadirkartal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppMainTests {

    @GetMapping("/info")
    public String getInfo() {
        return "DEVOPS INFO : ";
    }
}
