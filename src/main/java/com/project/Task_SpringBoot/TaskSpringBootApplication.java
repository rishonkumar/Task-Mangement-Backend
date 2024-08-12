package com.project.Task_SpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.project.Task_SpringBoot.reposistory")
public class TaskSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskSpringBootApplication.class, args);
	}

}
