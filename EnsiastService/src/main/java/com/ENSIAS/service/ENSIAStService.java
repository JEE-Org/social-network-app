package com.ENSIAS.service;

import com.ENSIAS.model.ENSIASt;
import com.ENSIAS.model.ENSIAStRegistrationRequest;
import com.ENSIAS.repository.EnsiastRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ENSIAStService implements IENSIAStSerivces {

    EnsiastRepository ensiastRepository;

    @Override
    public void registerENSIASt(ENSIAStRegistrationRequest request) {
        ENSIASt ensiaSt = ENSIASt.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .promo(request.getPromo())
                .build();
        ensiastRepository.saveAndFlush(ensiaSt);
    }
}
