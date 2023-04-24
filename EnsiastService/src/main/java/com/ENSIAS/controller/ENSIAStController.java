package com.ENSIAS.controller;


import com.ENSIAS.model.ENSIAStRegistrationRequest;
import com.ENSIAS.service.ENSIAStService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class ENSIAStController {

    private final ENSIAStService ensiaStService;
    @GetMapping
    public String get(){
        return "hello world";
    }

    @PostMapping
    public void registerENSIASt(@RequestBody ENSIAStRegistrationRequest request){
        ensiaStService.registerENSIASt(request);
    }
}
