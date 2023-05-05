package com.ENSIAS.service;

import com.ENSIAS.model.*;
import com.ENSIAS.repository.EnsiastRepository;
import com.ENSIAS.repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ENSIAStService implements IENSIAStSerivces {

    EnsiastRepository ensiastRepository;

    RoleRepository roleRepository;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


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
                //.password(request.getPassword())
                .state(STATE.INACTIF)
                .build();
        Set<Role> ensiastRoles = new HashSet<>();
        ensiastRoles.add(Role.USER);
        ensiaSt.setRoles(ensiastRoles);
        roleRepository.saveAndFlush(Role.USER);
        ensiastRepository.saveAndFlush(ensiaSt);


        return ensiaSt;
    }

    @Override
    public String login(LoginRequest request){
        Optional<ENSIASt> ensiaSt = ensiastRepository.findByEmail(request.getEmail());
        if(ensiaSt.isEmpty()){
            return String.format("%s : doesn't exist",request.getEmail());
        }
        String psswd = request.getPassword();
        String encodedPsswd = ensiaSt.get().getPassword();
        boolean isPsswdCorrect = bCryptPasswordEncoder.matches(psswd,encodedPsswd);
        //psswd.equals(encodedPsswd)
        if(isPsswdCorrect){
            ENSIASt ensiaSt1 = ensiaSt.get();
            ensiaSt1.setState(STATE.ACTIF);
            ensiastRepository.saveAndFlush(ensiaSt1);
            return "Logged successfully";
        } else {
            return "Password error";
        }
    }

    @Override
    public List<ENSIASt> findAll() {
        return ensiastRepository.findAll();
    }

    @Override
    public Optional<ENSIASt>  findByEmail(String email) {
        Optional<ENSIASt> ensiaSt = ensiastRepository.findByEmail(email);
        return ensiaSt;
    }

    @Override
    public Optional<ENSIASt> findByLastName(String lastName) {
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
