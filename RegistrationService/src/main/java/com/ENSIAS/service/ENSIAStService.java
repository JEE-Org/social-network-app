package com.ENSIAS.service;

import com.ENSIAS.model.ENSIASt;
import com.ENSIAS.model.LoginRequest;
import com.ENSIAS.model.RegistrationRequest;
import com.ENSIAS.model.Role;
import com.ENSIAS.repository.EnsiastRepository;
import com.ENSIAS.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ENSIAStService implements IENSIAStSerivces {

    EnsiastRepository ensiastRepository;

    RoleRepository roleRepository;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public ENSIASt registerENSIASt(RegistrationRequest request) {
        if(findByEmail(request.getEmail()).isPresent()) throw new RuntimeException("ENSIASt already exists !");
        ENSIASt ensiaSt = ENSIASt.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .promo(request.getPromo())
                .field(request.getField())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                //.password(request.getPassword())
                .build();
        ensiastRepository.saveAndFlush(ensiaSt);
        roleRepository.saveAndFlush(Role.USER);

        return ensiaSt;
    }

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
