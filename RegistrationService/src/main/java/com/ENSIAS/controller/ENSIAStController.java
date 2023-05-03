package com.ENSIAS.controller;


import com.ENSIAS.model.ENSIASt;
import com.ENSIAS.model.LoginRequest;
import com.ENSIAS.model.RegistrationRequest;
import com.ENSIAS.service.ENSIAStService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ENSIAStController {

    private final ENSIAStService ensiaStService;

    @GetMapping("/home")
    public String get(){
        return "hello world";
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerENSIASt(@RequestBody RegistrationRequest request){
        ENSIASt ensiaSt = ensiaStService.registerENSIASt(request);
        if(ensiaSt==null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This email is already registered");
        }
        return ResponseEntity.ok("ENSIASt created");
    }

    @GetMapping("/signup")
    public String sginup(){
        return "it's signup";
    }


    //Changed the return type
    @PostMapping("/login")
    public String loginENSIASt(@RequestBody LoginRequest request){
        return ensiaStService.login(request);
    }

    @GetMapping("/login")
    public String login(){
        return "it's login";
    }

    @GetMapping("/logOUT")
    public String logOUT(){
        return "it's logOUT";
    }

}
