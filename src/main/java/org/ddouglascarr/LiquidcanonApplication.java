package org.ddouglascarr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class LiquidcanonApplication extends WebMvcConfigurerAdapter
{

	public static void main(String[] args) {
		SpringApplication.run(LiquidcanonApplication.class, args);
	}


}
