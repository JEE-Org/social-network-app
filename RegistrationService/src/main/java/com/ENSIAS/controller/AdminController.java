package com.ENSIAS.controller;


import com.ENSIAS.model.ENSIASt;
import com.ENSIAS.service.ENSIAStService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    ENSIAStService ensiaStService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/active")
    List<String> findActifENSIASts(){
        Optional<List<ENSIASt>> ensiaSts = ensiaStService.findActifENSIASts();
        return ensiaSts
                .stream()
                .flatMap(List::stream)
                .map(ENSIASt::toString)
                .toList();
    }


}
