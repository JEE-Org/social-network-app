package com.ENSIAS.model;


import jakarta.persistence.*;
import lombok.*;

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


    ENSIASt(String firstName, String lastName, String email,String password,Integer promo){
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.promo=promo;
        this.password=password;
    }

}
