package com.ENSIAS.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.*;

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
    private String state;
    
//    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @ManyToMany(fetch = FetchType.EAGER)
    //    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();



    ENSIASt(String firstName, String lastName, String email,Integer promo,String field, String password){
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.promo=promo;
        this.password=password;
        this.field=field;
    }

    public ENSIASt(ENSIASt ensiaSt) {
        this.id=ensiaSt.id;
        this.firstName=ensiaSt.firstName;
        this.lastName=ensiaSt.lastName;
        this.email=ensiaSt.email;
        this.promo=ensiaSt.promo;
        this.field=ensiaSt.field;
        this.password=ensiaSt.password;
        this.state=ensiaSt.state;
        this.roles=ensiaSt.roles;
    }

}
