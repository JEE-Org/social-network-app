package com.ENSIAS.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegistrationRequest {

    private String firstName;
    private String lastName;
    private String email;
    private Integer promo;
    private String field;
    private String password;

}
