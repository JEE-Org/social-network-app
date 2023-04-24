package com.ENSIAS.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ENSIAStRegistrationRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Integer promo;

}
