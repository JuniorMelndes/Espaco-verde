package com.espacoverde;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("com.espacoverde.dao")
public class EspacoVerdeApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(EspacoVerdeApplication.class, args);
	}

}
