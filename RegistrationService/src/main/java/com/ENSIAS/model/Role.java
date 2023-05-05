package com.ENSIAS.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    public final static Role USER = new Role("USER");
    public final static Role ADMIN = new Role("ADMIN");


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String role;

    public Role(String role){
        this.role=role;
    }

}

//public enum Role {
//    USER,
//    ADMIN,
//}