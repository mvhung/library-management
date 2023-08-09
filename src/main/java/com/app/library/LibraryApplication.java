package com.app.library;

import com.app.library.auth.RegisterRequest;
import com.app.library.model.enum_class.RoleName;
import com.app.library.service.impl.AuthenticationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
}
//	@Bean
//	public CommandLineRunner commandLineRunner(
//			AuthenticationService service
//	) {
//		return args -> {
//			var admin1 = RegisterRequest.builder()
//					.firstName("Admin")
//					.lastName("Admin")
//					.username("Admin 1")
//					.email("admin1@mail.com")
//					.password("password")
//					.roleName(RoleName.ADMIN)
//					.build();
//			System.out.println("Admin token: " + service.registerAdmin(admin1).getToken());
//
//			var admin2 = RegisterRequest.builder()
//					.firstName("Admin")
//					.lastName("Admin")
//					.username("Admin 2")
//					.email("admin2@mail.com")
//					.password("password")
//					.roleName(RoleName.ADMIN)
//					.build();
//			System.out.println("Admin token: " + service.registerAdmin(admin2).getToken());
//
//		};
//	}
}
