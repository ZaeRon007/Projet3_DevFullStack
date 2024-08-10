package com.chatop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.chatop.model.rentals;
import com.chatop.model.user;
import com.chatop.service.rentalsService;
import com.chatop.service.userService;

@SpringBootApplication
public class ChatopApplication implements CommandLineRunner{

	@Autowired
	private rentalsService rentalsService;

	@Autowired
	private userService userService;

	public static void main(String[] args) {
		SpringApplication.run(ChatopApplication.class, args);
	}

	@Override
	public void run(String ... args) throws Exception {

		Iterable<user> users = userService.getUsers();
		users.forEach(user -> System.out.println(user.getName()));

		Iterable<rentals> rentals = rentalsService.getRentals();
		rentals.forEach(rental -> System.out.println(rental.getName()));
	}

}
