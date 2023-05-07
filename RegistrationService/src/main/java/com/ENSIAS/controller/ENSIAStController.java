package com.ENSIAS.controller;


import com.ENSIAS.model.AuthResponse;
import com.ENSIAS.model.ENSIASt;
import com.ENSIAS.model.LoginRequest;
import com.ENSIAS.model.RegistrationRequest;
import com.ENSIAS.service.ENSIAStService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return ensiaStService.checkRegistration(ensiaSt);
    }

    @GetMapping("/signup")
    public String sginup(){
        return "it's signup";
    }


    @PostMapping("/login")
    public AuthResponse loginENSIASt(@RequestBody LoginRequest request){
        return ensiaStService.login(request);
    }

    @GetMapping("/login")
    public String login(){
        return "it's login";
    }

    @GetMapping("/logut")
    public String logout(){
        return "it's logOUT";
    }

    @GetMapping("/ENSIASts")
    public List<ENSIASt> findAll(){
        return ensiaStService.findAll();
    }

    @GetMapping("/tests")
    public String test(){
        return "test";
    }

    @GetMapping("/ENSIASts/lastnames")
    public List<String> findByLastName(@RequestParam(value = "lastName") String lastName ){
        Optional<List<ENSIASt>> ensiaSt = ensiaStService.findByLastName(lastName);
        return ensiaSt
                .stream()
                .flatMap(List::stream)
                .map(ENSIASt::toString)
                .toList();
    }

    @GetMapping("/ENSIASts/promos")
    public List<ENSIASt> findByPromo(@RequestParam(value = "promo") Integer promo){
        Optional<List<ENSIASt>> ensiaSts = ensiaStService.findByPromo(promo);
        return ensiaSts
                .stream()
                .flatMap(List::stream)
                //.map(ENSIASt::toString)
                .toList();
    }

    @GetMapping("/ENSIASts/{promo}")
    public List<ENSIASt> findByPromoAndField(@PathVariable(value = "promo") Integer promo,
                                             @RequestParam(value = "field") String field){

        Optional<List<ENSIASt>> ensiaSts = ensiaStService.findByPromoAndField(promo,field);
        return ensiaSts
                .stream()
                .flatMap(List::stream)
                //.map(ENSIASt::toString)
                .toList();
    }

    @GetMapping("/ENSIASts/fields")
    public List<ENSIASt> findByField(@RequestParam(value = "field") String field){
        Optional<List<ENSIASt>> ensiaSts = ensiaStService.findByField(field);
        return ensiaSts
                .stream()
                .flatMap(List::stream)
                //.map(ENSIASt::toString)
                .toList();
    }
    ///{field}


}
