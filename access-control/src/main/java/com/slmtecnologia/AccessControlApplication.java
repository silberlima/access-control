package com.slmtecnologia;

import com.slmtecnologia.model.dto.RegisterRequest;
import com.slmtecnologia.service.core.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

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
					"admin@gmail.com",
					"password");
			log.info("Admin token: " + service.register(admin).accessToken());

		};
	}
}
