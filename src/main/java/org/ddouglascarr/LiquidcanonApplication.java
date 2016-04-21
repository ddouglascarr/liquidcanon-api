package org.ddouglascarr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class LiquidcanonApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiquidcanonApplication.class, args);
	}
}
