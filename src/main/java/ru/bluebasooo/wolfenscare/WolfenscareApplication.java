package ru.bluebasooo.wolfenscare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.bluebasooo.wolfenscare.repository.UserRepository;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = {UserRepository.class})
public class WolfenscareApplication {

	public static void main(String[] args) {
		SpringApplication.run(WolfenscareApplication.class, args);
	}

}
