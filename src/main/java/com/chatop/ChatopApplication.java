package com.chatop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// import com.chatop.model.rentals;
// import com.chatop.model.user;
// import com.chatop.services.rentalsService;
// import com.chatop.services.userService;

@SpringBootApplication
public class ChatopApplication implements CommandLineRunner{

	// @Autowired
	// private rentalsService rentalsService;

	// @Autowired
	// private userService userService;

	public static void main(String[] args) {
		SpringApplication.run(ChatopApplication.class, args);
	}

	@Override
	public void run(String ... args) throws Exception {

		// Iterable<user> users = userService.getUsers();
		// users.forEach(user -> System.out.println(user.getName()));

		// Iterable<rentals> rentals = rentalsService.getRentals();
		// rentals.forEach(rental -> System.out.println(rental.getName()));

		// user user = new user();
		// user.setName("Pedro");
		// user.setEmail("pedro@gmail.com");
		// user.setCreated_at("2024/01/12");
		// user.setUpdated_at("2024/01/15");

		// user = userService.addUser(user);

		// userService.getUsers().forEach(
		// 	u -> {
		// 		System.out.printf("User %s %s %s %s will be removed\n",u.getName(),u.getEmail(),u.getCreated_at(),u.getUpdated_at());
		// 		userService.removeUser(u);
		// 	}
		// );
	}

}
