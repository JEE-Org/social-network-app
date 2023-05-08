package com.ENSIAS.controller;


import com.ENSIAS.model.AuthResponse;
import com.ENSIAS.model.ENSIASt;
import com.ENSIAS.model.LoginRequest;
import com.ENSIAS.model.RegistrationRequest;
import com.ENSIAS.service.ENSIAStService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/ENSIASts")
public class ENSIAStController {

    private final ENSIAStService ensiaStService;

    @GetMapping("/home")
    public String get(){
        return "hello world";
    }

    @GetMapping
    public List<ENSIASt> findAll(){
        return ensiaStService.findAll();
    }

    @GetMapping("/lastnames")
    public List<String> findByLastName(@RequestParam(value = "lastname") String lastName ){
        Optional<List<ENSIASt>> ensiaSt = ensiaStService.findByLastName(lastName);
        return ensiaSt
                .stream()
                .flatMap(List::stream)
                .map(ENSIASt::toString)
                .toList();
    }

    @GetMapping("/promos")
    public List<ENSIASt> findByPromo(@RequestParam(value = "promo") Integer promo){
        Optional<List<ENSIASt>> ensiaSts = ensiaStService.findByPromo(promo);
        return ensiaSts
                .stream()
                .flatMap(List::stream)
                //.map(ENSIASt::toString)
                .toList();
    }

    @GetMapping("/{promo}")
    public List<ENSIASt> findByPromoAndField(@PathVariable(value = "promo") Integer promo,
                                             @RequestParam(value = "field") String field){
        Optional<List<ENSIASt>> ensiaSts = ensiaStService.findByPromoAndField(promo,field);
        return ensiaSts
                .stream()
                .flatMap(List::stream)
                //.map(ENSIASt::toString)
                .toList();
    }

    @GetMapping("/fields")
    public List<ENSIASt> findByField(@RequestParam(value = "field") String field){
        Optional<List<ENSIASt>> ensiaSts = ensiaStService.findByField(field);
        return ensiaSts
                .stream()
                .flatMap(List::stream)
                //.map(ENSIASt::toString)
                .toList();
    }
}
