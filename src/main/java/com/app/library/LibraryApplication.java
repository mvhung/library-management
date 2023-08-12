package com.app.library;

import com.app.library.auth.RegisterRequest;
import com.app.library.model.*;
import com.app.library.model.enum_class.RoleName;
import com.app.library.repository.*;
import com.app.library.service.impl.AuthenticationService;
import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
}
//	@Bean
//	public CommandLineRunner generateSampleUser(
//			PasswordEncoder passwordEncoder,
//			UserRepository userRepository) {
//		return args -> {
//			Faker faker = new Faker();
//			int numberOfUsers = 20;
//
//			for (int i = 0; i < numberOfUsers; i++) {
//				String firstName = faker.name().firstName();
//				String lastName = faker.name().lastName();
//				String username = faker.name().username();
//				String email = faker.internet().safeEmailAddress();
//				String password = "password";
//				RoleName roleName = RoleName.USER;
//				String avatarUrl = "https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/avatars/mAKI-top-anh-dai-dien-dep-chat-1.jpg";
//
//				User user = new User();
//				user.setFirstName(firstName);
//				user.setLastName(lastName);
//				user.setUsername(username);
//				user.setEmail(email);
//				user.setPassword(passwordEncoder.encode(password));
//				user.setRoleName(roleName);
//				user.setAvatarUrl(avatarUrl);
//
//				userRepository.save(user);
//			}
//		};
//	}
//	@Bean
//	public CommandLineRunner generateSampleCategory(
//			CategoryRepository categoryRepository
//	) {
//		return args -> {
//			Faker faker = new Faker();
//			int numberOfCategories = 10;
//
//			for (int i = 0; i < numberOfCategories; i++) {
//				Category category = new Category();
//				category.setCategoryName(faker.book().genre());
//				category.setCategoryDescription(faker.lorem().sentence());
//				categoryRepository.save(category);
//			}
//		};
//	}
//
//	@Bean
//	public CommandLineRunner generateSampleAuthor(
//			AuthorRepository authorRepository
//	) {
//		return args -> {
//			Faker faker = new Faker();
//			int numberOfAuthors = 10;
//
//			for (int i = 0; i < numberOfAuthors; i++) {
//				Author author = new Author();
//				author.setAuthorFullName(faker.name().fullName());
//				author.setAuthorIntroduce(faker.lorem().sentence());
//				author.setAuthorImageUrl("");
//				authorRepository.save(author);
//			}
//		};
//	}
//
//
//	@Bean
//	public CommandLineRunner generateSamplePublisher(
//			PublisherRepository publisherRepository) {
//		return args -> {
//			Faker faker = new Faker();
//			int numberOfPublishers = 10;
//
//			for (int i = 0; i < numberOfPublishers; i++) {
//				Publisher publisher = new Publisher();
//				publisher.setPublisherName(faker.book().publisher());
//				publisher.setPublisherIntroduce(faker.lorem().sentence());
//				publisher.setPublisherWebsiteUrl(faker.internet().url());
//				publisher.setPublisherImageUrl("");
//				publisherRepository.save(publisher);
//			}
//		};
//	}
//	@Bean
//	public CommandLineRunner generateSampleBook(
//			BookRepository bookRepository,
//			CategoryRepository categoryRepository,
//			PublisherRepository publisherRepository,
//			AuthorRepository authorRepository,
//			EntityManager entityManager
//	) {
//		return args ->{
//			Faker faker = new Faker();
//			int numberOfBooks = 20;
//
//			List<Category> categories = categoryRepository.findAll();
//			List<Publisher> publishers = publisherRepository.findAll();
//			List<Author> authors = authorRepository.findAll();
//
//			for (int i = 0; i < numberOfBooks; i++) {
//				Book book = new Book();
//				book.setBookTitle(faker.book().title());
//				book.setBookPublishedYear(faker.number().numberBetween(1900, 2023));
//				book.setBookQuantity(faker.number().numberBetween(1, 100));
//				book.setBookDescription(faker.lorem().sentence());
//				book.setBookImageLink(faker.internet().image());
//
////				// Choose a random category
////				Category category = categories.get(faker.random().nextInt(categories.size()));
////				book.setCategory(category);
////
////				// Choose a random publisher
////				Publisher publisher = publishers.get(faker.random().nextInt(publishers.size()));
////				book.setPublisher(publisher);
////
////				// Choose two random authors
////				List<Author> selectedAuthors = new ArrayList<>();
////				for (int j = 0; j < 2; j++) {
////					Author author = authors.get(faker.random().nextInt(authors.size()));
////					selectedAuthors.add(author);
////				}
////				book.setAuthors(selectedAuthors);
//
////				entityManager.persist(book);
//
//				bookRepository.save(book);
//			}
//		};
//	}
//
//
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
