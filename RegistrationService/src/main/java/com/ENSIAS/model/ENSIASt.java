package com.ENSIAS.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table
public class ENSIASt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;
    private String email;
    private Integer promo;
    private String field;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>(Collections.singletonList(new Role("USER")));



    ENSIASt(String firstName, String lastName, String email,Integer promo,String field, String password){
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.promo=promo;
        this.password=password;
    }

    public ENSIASt(ENSIASt ensiaSt) {
        this.id=ensiaSt.id;
        this.firstName=ensiaSt.firstName;
        this.lastName=ensiaSt.lastName;
        this.email=ensiaSt.email;
        this.promo=ensiaSt.promo;
        this.field=ensiaSt.field;
        this.password=ensiaSt.password;
        this.roles=ensiaSt.roles;
    }

}
