package com.ENSIAS.controller;


import com.ENSIAS.model.ENSIASt;
import com.ENSIAS.model.LoginRequest;
import com.ENSIAS.model.RegistrationRequest;
import com.ENSIAS.service.ENSIAStService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;



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
    public ResponseEntity<String> registerENSIASt(@RequestBody RegistrationRequest request) {
        ENSIASt newENSIASt = ensiaStService.registerENSIASt(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("ENSIASt registered successfully");
    }


    @PostMapping("/login")
    public void loginENSIASt(@RequestBody LoginRequest request){

    }




}
