package com.ENSIAS.security;

import com.ENSIAS.repository.EnsiastRepository;
import com.ENSIAS.repository.RoleRepository;
import com.ENSIAS.service.ENSIAStService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ENSIAStDetailsService implements UserDetailsService {


    @Autowired
    private ENSIAStService ensiastService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) ensiastService
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

    }
}
