package com.neosoft.main;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import lombok.extern.slf4j.Slf4j;


@EnableJpaRepositories("com.neosoft.*")
@ComponentScan(basePackages = { "com.neosoft.*" })
@EntityScan("com.neosoft.*")
@SpringBootApplication
@Slf4j
public class SpringBootCrudApplication {
//	static final Logger log = 
//	        LoggerFactory.getLogger(SpringBootCrudApplication.class);

	public static void main(String[] args) {
		log.info("************Welcome to Spring Boot JPA CRUD Rest API************");
		SpringApplication.run(SpringBootCrudApplication.class, args);
	}

}
