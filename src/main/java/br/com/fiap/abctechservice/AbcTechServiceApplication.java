package br.com.fiap.abctechservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class AbcTechServiceApplication {

    @GetMapping()
    public static void main(String[] args) {
		SpringApplication.run(AbcTechServiceApplication.class, args);
	}

}
