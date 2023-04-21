package com.ENSIAS.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Signin")
public class ENSIAStController {

    @GetMapping
    public String get(){
        return "hello world";
    }
}
