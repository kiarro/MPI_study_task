package com.mpi.alienresearch;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import com.mpi.alienresearch.dao.UserRepository;
import com.mpi.alienresearch.model.User;
import com.mpi.alienresearch.model.enums.UserRole;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.mpi")
@ComponentScan("com.mpi.alienresearch")
public class DemoApplication {
	// private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// @Bean
	// public CommandLineRunner demo(UserRepository repository) {
	// 	return (args) -> {
	// 		// save a few customers
	// 		repository.save(new User("0001", "andrew", "pass1", UserRole.Researcher,
	// 				"Andrew", "Rayan", LocalDate.of(1980, 10, 21)));
	// 		repository.save(new User("0002", "ruby", "pass1", UserRole.Director,
	// 				"Ruby", "Rose", LocalDate.of(2002, 6, 13)));
	// 		repository.save(new User("0003", "rean", "pass1", UserRole.Admin,
	// 				"Rean", "Schwarzer", LocalDate.of(2004, 4, 25)));

	// 		// fetch all customers
	// 		log.info("Users found with findAll():");
	// 		log.info("-------------------------------");
	// 		for (User customer : repository.findAll()) {
	// 			log.info(customer.toString());
	// 		}
	// 		log.info("");

	// 		// fetch an individual customer by ID
	// 		User customer = repository.findById(44L);
	// 		log.info("Customer found with findById(1L):");
	// 		log.info("--------------------------------");
	// 		log.info(customer.toString());
	// 		log.info("");

	// 		// fetch customers by last name
	// 		log.info("Customer found with findByLastName('Bauer'):");
	// 		log.info("--------------------------------------------");
	// 		repository.findByUsername("ruby").forEach(bauer -> {
	// 			log.info(bauer.toString());
	// 		});
	// 		// for (Customer bauer : repository.findByLastName("Bauer")) {
	// 		// log.info(bauer.toString());
	// 		// }
	// 		log.info("");
	// 	};
	// }
}
