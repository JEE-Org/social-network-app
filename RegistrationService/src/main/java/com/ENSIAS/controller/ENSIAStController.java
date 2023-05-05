package com.ENSIAS.controller;


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

    @GetMapping("/logut")
    public String logout(){
        return "it's logOUT";
    }

    @GetMapping("/ENSAISts")
    public List<ENSIASt> findAll(){
        return ensiaStService.findAll();
    }

    @GetMapping("tests")
    public String test(){
        return "test";
    }

    @GetMapping("ENSIASts/{lastName}")
    public String findByLastName(@PathVariable String lastName ){
       Optional<ENSIASt> ensiaSt = ensiaStService.findByLastName(lastName);
       if (ensiaSt.isPresent()){
           return ensiaSt.get().getLastName();
       }
       else {
           return String.format("%s doesn't exist",lastName);
       }
    }

    @GetMapping("ENSIASts/{promo}")
    public List<ENSIASt> findByPromo(@PathVariable Integer promo){
        Optional<List<ENSIASt>> ensiaSts = ensiaStService.findByPromo(promo);
        List<ENSIASt> ensiaStList = new ArrayList<>();
        ensiaSts.orElse(Collections.emptyList()).addAll(ensiaStList);
        return ensiaStList;
    }

    @GetMapping("ENSIASts/{promo}/{field}")
    public List<ENSIASt> findByPromoAndField(@PathVariable(value = "promo") Integer promo,
                                             @PathVariable(value = "field") String field){

        Optional<List<ENSIASt>> ensiaSts = ensiaStService.findByPromoAndField(promo,field);
        List<ENSIASt> ensiaStList = new ArrayList<>();
        ensiaSts.orElse(Collections.emptyList()).addAll(ensiaStList);
        return ensiaStList;
    }

    @GetMapping("ENSIASts/{field}")
    public List<ENSIASt> findByField(@PathVariable String field){
        Optional<List<ENSIASt>> ensiaSts = ensiaStService.findByField(field);
        List<ENSIASt> ensiaStList = new ArrayList<>();
        ensiaSts.orElse(Collections.emptyList()).addAll(ensiaStList);
        return ensiaStList;
    }


}
