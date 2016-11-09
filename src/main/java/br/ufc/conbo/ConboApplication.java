package br.ufc.conbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class ConboApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConboApplication.class, args);
	}
}
