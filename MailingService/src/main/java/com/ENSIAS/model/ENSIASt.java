package com.ENSIAS.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ENSIASt {

    private String firstName;
    private String lastName;
    private String email;

}
