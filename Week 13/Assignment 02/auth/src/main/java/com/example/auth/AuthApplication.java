package com.example.auth;

import com.example.auth.config.RsaKeyConfigProperties;
import com.example.auth.entity.User;
import com.example.auth.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableConfigurationProperties(RsaKeyConfigProperties.class)
@SpringBootApplication
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	@Bean
	public CommandLineRunner initializeUser(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
		return args -> {
			User user = new User();
			user.setUsername("sheren");
			user.setEmail("sheren@gmail.com");
			user.setPassword(passwordEncoder.encode("123"));

			userRepository.save(user);
		};
	}

}
