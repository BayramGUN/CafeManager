package com.inn.cafe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.inn.cafe.user.dao.UserRepository;
import com.inn.cafe.user.data.User;
import com.inn.cafe.user.data.enums.Role;
import com.inn.cafe.user.data.enums.Status;

@SpringBootApplication
public class CafeManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CafeManagementApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (!userRepository.existsByUsername("admin")) {
				var admin = User.builder()
								 .firstName("default")
								 .lastName("administrator")
								 .email("admin@default.com")
								 .username("admin")
								 .contactNumber("1111111111")
								 .password(passwordEncoder.encode("admin"))
								 .role(Role.ADMIN)
								 .status(Status.ACTIVE)
								 .build();
				userRepository.save(admin);
			}
		};
	}

}
