package ru.mis.uchastkovy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.mis.uchastkovy.security.model.User;
import ru.mis.uchastkovy.security.repo.UserRepository;

@SpringBootApplication
public class UchastkovyApplication {

	public static void main(String[] args) {
		SpringApplication.run(UchastkovyApplication.class, args);
	}

	@Autowired
	private UserRepository repository;

	@Bean
	public CommandLineRunner CommandLineRunnerBean() {
		return (args) -> {
			User user3 = repository.findByLogin("user3");
			System.out.println(user3.getPassword());
		};
	}
}
