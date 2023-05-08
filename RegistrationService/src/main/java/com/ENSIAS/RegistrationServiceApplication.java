package com.ENSIAS;

import com.ENSIAS.model.RegistrationRequest;
import com.ENSIAS.service.ENSIAStService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
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
                "haitam@test.com",
                2024,
                "GL",
                "test"
        );

        ensiaStService.registerENSIASt(request);
        ensiaStService.addRole(request.getEmail());
    }
}