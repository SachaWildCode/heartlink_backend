package fr.slghive.heartlink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableJpaAuditing
@EnableMethodSecurity
public class HeartlinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeartlinkApplication.class, args);
	}

}
