package com.ENSIAS.repository;

import com.ENSIAS.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = """
      select t from Token t inner join ENSIASt e\s
      on t.ensiaSt.ensiast_id = e.ensiast_id\s
      where e.ensiast_id = :id and (t.expired = false or t.revoked = false)\s
      """)

    List<Token> findAllValidTokenByUser(Integer id);
    Optional<Token> findByToken(String token);
}
