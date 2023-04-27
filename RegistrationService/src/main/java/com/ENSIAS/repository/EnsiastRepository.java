package com.ENSIAS.repository;

import com.ENSIAS.model.ENSIASt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface EnsiastRepository extends JpaRepository<ENSIASt, Integer> {

    public Optional<ENSIASt>  findByEmail(String email);
    public Optional<ENSIASt> findByLastName(String lastName);
    public Optional<List<ENSIASt>> findByPromo(Integer promo);
    public Optional<List<ENSIASt>> findByPromoAndField(Integer promo, String field);
    public Optional<List<ENSIASt>> findByField(String field);

}
