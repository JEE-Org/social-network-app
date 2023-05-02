package com.ENSIAS.controller;


import com.ENSIAS.model.ENSIASt;
import com.ENSIAS.model.LoginRequest;
import com.ENSIAS.model.RegistrationRequest;
import com.ENSIAS.service.ENSIAStService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/ENSIASts")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200") // allow requests from the specified origin
public class ENSIAStController {

    private final ENSIAStService ensiaStService;
    @GetMapping
    public String get(){
        return "hello world";
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerENSIASt(@RequestBody RegistrationRequest request) {
        try {
            ENSIASt newENSIASt = ensiaStService.registerENSIASt(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("ENSIASt registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register ENSIASt: " + e.getMessage());
        }
    }


    @PostMapping("/login")
    public void loginENSIASt(@RequestBody LoginRequest request){

    }




}
