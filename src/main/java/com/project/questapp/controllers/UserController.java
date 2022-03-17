package com.project.questapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.questapp.entities.User;
import com.project.questapp.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<User> getAllUsers() {
		return this.userService.getAllUsers();
	}

	@PostMapping
	public User createUser(@RequestBody User user) {
		return this.userService.createUser(user);
	}

	@GetMapping("/{userId}")
	public User getOneUser(@PathVariable Integer userId) {
		// custom Exception
		return this.userService.getOneUser(userId);
	}

	// Var olan idli bir useri değiştirmek için kullanılır.Üzerinde değişiklik
	// yapılabilir
	@PutMapping({ "/userId" })
	public User updateOneUser(@PathVariable Integer userId, @RequestBody User user) {
		return this.userService.updateOneUser(userId, user);
	}

	@DeleteMapping({ "/userId" })
	public void deleteOneUser(Integer userId) {
		this.userService.deleteOneUser(userId);
	}

}
