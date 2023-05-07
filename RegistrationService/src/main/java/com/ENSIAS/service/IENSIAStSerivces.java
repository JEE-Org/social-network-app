package com.ENSIAS.service;

import com.ENSIAS.enums.State;
import com.ENSIAS.model.AuthResponse;
import com.ENSIAS.model.ENSIASt;
import com.ENSIAS.model.LoginRequest;
import com.ENSIAS.model.RegistrationRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IENSIAStSerivces {

    public ENSIASt registerENSIASt(RegistrationRequest request);
    public ResponseEntity<String> checkRegistration(ENSIASt ensiaSt);
    public AuthResponse login(LoginRequest request);
    public List<ENSIASt> findAll();
    public Optional<ENSIASt> findByEmail(String email);
    public Optional<List<ENSIASt>> findByLastName(String lastName);
    public Optional<List<ENSIASt>> findByPromo(Integer promo);
    public Optional<List<ENSIASt>> findByPromoAndField(Integer promo, String field);
    public Optional<List<ENSIASt>> findByField(String field);
    public Optional<List<ENSIASt>> findActifENSIASts(State state);



}
