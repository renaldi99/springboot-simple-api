package com.enigma.learnspringboot.controller;

import com.enigma.learnspringboot.entity.Hello;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/hello/{name}")
    public String getName(@PathVariable String name) {
        return "My name is : " + name;
    }

    @GetMapping("/hello")
    public String urlName(@RequestParam String name) {
        return "Url name : " + name;
    }

//    @PostMapping("/reqBody")
//    public String reqBody(@RequestBody HashMap<String, String> mapBody) {
//        return "request body " + mapBody;
//    }

    @PostMapping("/reqBody")
    public Hello reqBody(@RequestBody Hello data) {
        return data;
    }
}