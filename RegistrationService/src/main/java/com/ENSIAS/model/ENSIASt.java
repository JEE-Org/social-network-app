package com.ENSIAS.model;


import com.ENSIAS.enums.Role;
import com.ENSIAS.enums.State;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table
public class ENSIASt implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ensiast_id;

    private String firstName;
    private String lastName;
    private String email;
    private Integer promo;
    private String field;
    private String password;

    @Enumerated(EnumType.STRING)
    private State state;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "ensiaSt")
    private List<Token> tokens;

    public ENSIASt(Optional<ENSIASt> ensiaSt) {
        this.ensiast_id =ensiaSt.get().getEnsiast_id();
        this.firstName=ensiaSt.get().getFirstName();
        this.lastName=ensiaSt.get().getLastName();
        this.email=ensiaSt.get().getEmail();
        this.promo=ensiaSt.get().getPromo();
        this.field=ensiaSt.get().getField();
        this.password=ensiaSt.get().getPassword();
        this.state=ensiaSt.get().getState();
        this.role =ensiaSt.get().getRole();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString(){
        return "First name :"+firstName+
                ", Last name :"+lastName+
                ", Email :"+email+
                ", Promo :"+promo+
                ", Field :"+field;
    }
}
