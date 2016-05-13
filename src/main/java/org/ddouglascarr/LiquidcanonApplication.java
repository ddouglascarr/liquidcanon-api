package org.ddouglascarr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class LiquidcanonApplication
{

	public static void main(String[] args) {
		System.out.println("Running main");
		SpringApplication.run(LiquidcanonApplication.class, args);
	}


}
