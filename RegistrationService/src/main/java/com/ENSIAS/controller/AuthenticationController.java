package com.ENSIAS.controller;


import com.ENSIAS.model.AuthResponse;
import com.ENSIAS.model.ENSIASt;
import com.ENSIAS.model.LoginRequest;
import com.ENSIAS.model.RegistrationRequest;
import com.ENSIAS.service.ENSIAStService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
@AllArgsConstructor
public class AuthenticationController {

    private final ENSIAStService ensiaStService;

    @PostMapping("/signup")
    public ResponseEntity<String> registerENSIASt(@RequestBody RegistrationRequest request){
        ENSIASt ensiaSt = ensiaStService.registerENSIASt(request);
        return ensiaStService.checkRegistration(ensiaSt);
    }

    @PostMapping("/login")
    public AuthResponse loginENSIASt(@RequestBody LoginRequest request){
        return ensiaStService.login(request);
    }


}
