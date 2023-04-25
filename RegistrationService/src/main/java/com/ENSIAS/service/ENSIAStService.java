package com.ENSIAS.service;

import com.ENSIAS.model.ENSIASt;
import com.ENSIAS.model.RegistrationRequest;
import com.ENSIAS.repository.EnsiastRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ENSIAStService implements IENSIAStSerivces {

    EnsiastRepository ensiastRepository;

    @Override
    public void registerENSIASt(RegistrationRequest request) {
        ENSIASt ensiaSt = ENSIASt.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .promo(request.getPromo())
                .field(request.getField())
                .password(request.getPassword())
                .build();
        ensiastRepository.saveAndFlush(ensiaSt);
    }

    @Override
    public List<ENSIASt> findAll() {
        return ensiastRepository.findAll();
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
