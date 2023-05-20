package com.ENSIAS;

import com.ENSIAS.model.RegistrationRequest;
import com.ENSIAS.service.ENSIAStService;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableEncryptableProperties
public class RegistrationServiceApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(RegistrationServiceApplication.class,args);
    }

    @Autowired
    ENSIAStService ensiaStService;
    @Override
    public void run(String... args) throws Exception{
        RegistrationRequest request = new RegistrationRequest(
                "haitam",
                "elhayani",
                "haytamelhayani@gmail.com",
                2024,
                "GL",
                "test"
        );

        ensiaStService.registerENSIASt(request);
        ensiaStService.addRole(request.getEmail());
    }
}