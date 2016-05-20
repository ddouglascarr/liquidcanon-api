package org.ddouglascarr;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = RepositoryRestMvcAutoConfiguration.class)
@EnableTransactionManagement
public class LiquidcanonApplication
{

	public static void main(String[] args) {
		System.out.println("Running main");
		SpringApplication.run(LiquidcanonApplication.class, args);
	}

}
