package com.slmtecnologia;

import com.slmtecnologia.service.core.AuthenticationService;
import com.slmtecnologia.model.dto.RegisterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static com.slmtecnologia.model.enuns.Role.ADMIN;
import static com.slmtecnologia.model.enuns.Role.PERSON;

@SpringBootApplication
@Slf4j
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AccessControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccessControlApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {

			var admin = new RegisterRequest(
					"Admin",
					"Admin",
					"admin@mail.com",
					"password",
					ADMIN);
			log.info("Admin token: " + service.register(admin).getAccessToken());

			var manager = new RegisterRequest(
					"Person",
					"Person",
					"person@mail.com",
					"password",
					PERSON);
			log.info("Person token: " + service.register(manager).getAccessToken());

		};
	}
}
