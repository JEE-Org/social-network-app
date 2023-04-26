package com.ENSIAS.controller;


import com.ENSIAS.model.ENSIASt;
import com.ENSIAS.model.LoginRequest;
import com.ENSIAS.model.RegistrationRequest;
import com.ENSIAS.service.ENSIAStService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ENSIASts")
@AllArgsConstructor
public class ENSIAStController {

    private final ENSIAStService ensiaStService;
    @GetMapping
    public String get(){
        return "hello world";
    }

    @PostMapping("/signup")
    public void registerENSIASt(@RequestBody RegistrationRequest request){
        ensiaStService.registerENSIASt(request);
    }

    @PostMapping("/login")
    public void loginENSIASt(@RequestBody LoginRequest request){

    }




}
