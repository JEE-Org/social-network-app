package com.ENSIAS.service;

import com.ENSIAS.model.ENSIASt;
import com.ENSIAS.model.LoginRequest;
import com.ENSIAS.model.RegistrationRequest;

import java.util.List;
import java.util.Optional;

public interface IENSIAStSerivces {

    public ENSIASt registerENSIASt(RegistrationRequest request);

    public String login(LoginRequest request);
    public List<ENSIASt> findAll();

    public Optional<ENSIASt> findByEmail(String email);
    public Optional<ENSIASt> findByLastName(String lastName);
    public Optional<List<ENSIASt>> findByPromo(Integer promo);
    public Optional<List<ENSIASt>> findByPromoAndField(Integer promo, String field);
    public Optional<List<ENSIASt>> findByField(String field);


}
