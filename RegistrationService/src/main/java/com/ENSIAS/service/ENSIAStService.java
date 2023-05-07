package com.ENSIAS.service;

import com.ENSIAS.enums.Role;
import com.ENSIAS.enums.State;
import com.ENSIAS.model.*;
import com.ENSIAS.repository.EnsiastRepository;
import com.ENSIAS.repository.TokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ENSIAStService implements IENSIAStSerivces {

    private final EnsiastRepository ensiastRepository;

    private final TokenRepository tokenRepository;

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Override
    public ENSIASt registerENSIASt(RegistrationRequest request) {
        if(findByEmail(request.getEmail()).isPresent()) return null;
        ENSIASt ensiaSt = ENSIASt.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .promo(request.getPromo())
                .field(request.getField())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .state(State.INACTIF)
                .role(Role.USER)
                .build();
        ensiastRepository.saveAndFlush(ensiaSt);
//        var jwtToken = jwtService.generateToken(ensiaSt);
//        return AuthResponse.builder()
//                .token(jwtToken)
//                .build();
        return ensiaSt;
    }
    
    public ResponseEntity<String> checkRegistration(ENSIASt ensiaSt){
        if(ensiaSt==null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This email is already registered");
        }
        return ResponseEntity.ok("ENSIASt created");
    }

    @Override
    public AuthResponse login(LoginRequest request){
        Optional<ENSIASt> ensiaSt = ensiastRepository.findByEmail(request.getEmail());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException exception){
            String response;
            if(ensiaSt.isEmpty()){
                response = String.format("%s : doesn't exist",request.getEmail());
                return AuthResponse.builder()
                        .accessToken(response)
                        .build();
            }
            response="Password error";
            return AuthResponse.builder()
                    .accessToken(response)
                    .build();
        }
        ENSIASt ensiaSt1 = new ENSIASt(ensiaSt);
        ensiaSt1.setState(State.ACTIF);
        ensiastRepository.saveAndFlush(ensiaSt1);
        var jwtToken = jwtService.generateToken(ensiaSt1);
        revokeAllUserTokens(ensiaSt1);
        saveUserToken(ensiaSt1, jwtToken);
        return AuthResponse.builder()
                .accessToken(jwtToken)
                .build();
//        String psswd = request.getPassword();
//        String encodedPsswd = ensiaSt.get().getPassword();
//        boolean isPsswdCorrect = bCryptPasswordEncoder.matches(psswd,encodedPsswd);
//        //psswd.equals(encodedPsswd)
//        if(isPsswdCorrect){
//            ENSIASt ensiaSt1 = ensiaSt.get();
//            ensiaSt1.setState(STATE.ACTIF);
//            ensiastRepository.saveAndFlush(ensiaSt1);
//            return "Logged successfully";
//        } else {
//            return "Password error";
//        }
    }
    private void saveUserToken(ENSIASt ensiaSt, String jwtToken) {
        var token = Token.builder()
                .ensiaSt(ensiaSt)
                .token(jwtToken)
                .tokenType("BEARER")
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(ENSIASt ensiaSt) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(ensiaSt.getEnsiast_id());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    @Override
    public List<ENSIASt> findAll() {
        return ensiastRepository.findAll();
    }

    @Override
    public Optional<ENSIASt>  findByEmail(String email) {
        return ensiastRepository.findByEmail(email);
    }

    @Override
    public Optional<List<ENSIASt>> findByLastName(String lastName) {
        return ensiastRepository.findByLastName(lastName);
    }

    @Override
    public Optional<List<ENSIASt>> findByPromo(Integer promo) {
        return ensiastRepository.findByPromo(promo);
    }

    @Override
    public Optional<List<ENSIASt>> findByPromoAndField(Integer promo, String field) {
        return ensiastRepository.findByPromoAndField(promo,field);
    }

    @Override
    public Optional<List<ENSIASt>> findByField(String field) {
        return ensiastRepository.findByField(field);
    }
}
