package br.com.fiap.abctechservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class AbcTechServiceApplication {

    @GetMapping()
    public static void main(String[] args) {
		SpringApplication.run(AbcTechServiceApplication.class, args);
	}

}
