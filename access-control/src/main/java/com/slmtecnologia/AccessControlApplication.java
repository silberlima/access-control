package com.slmtecnologia;

import com.slmtecnologia.security.service.impl.AuthenticationService;
import com.slmtecnologia.security.model.dto.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static com.slmtecnologia.security.model.enuns.Role.ADMIN;
import static com.slmtecnologia.security.model.enuns.Role.PERSON;

@SpringBootApplication
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
			var admin = RegisterRequest.builder()
					.firstName("Admin")
					.lastname("Admin")
					.email("admin@mail.com")
					.password("password")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

			var manager = RegisterRequest.builder()
					.firstName("Person")
					.lastname("Name")
					.email("person@mail.com")
					.password("password")
					.role(PERSON)
					.build();
			System.out.println("Person token: " + service.register(manager).getAccessToken());

		};
	}
}
