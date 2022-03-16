package com.project.questapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.questapp.entities.User;
import com.project.questapp.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}

	@PostMapping
	public User createUser(@RequestBody User user) {
		return this.userRepository.save(user);
	}

	@GetMapping("/{userId}")
	public User getOneUser(@PathVariable Integer userId) {
		// custom Exception
		return this.userRepository.findById(userId).orElse(null); // user database de olmazsa null dönecek
	}

	@PutMapping({ "/userId" }) // Var olan idli bir useri değiştirmek için kullanılır.Üzerinde değişiklik
								// yapılabilir
	public User updateOneUser(@PathVariable Integer userId, @RequestBody User user) {
		Optional<User> user1 = userRepository.findById(userId); // update edilmeden önce userIdsi belirtilen useri
																// bulur.
		if (user1.isPresent()) // optinal alınan user databasede mevcut mu değil mi diye takibi yapılabilir
		{
			User foundUser = user1.get(); // optiona gelen user alındı
			foundUser.setUsername(user.getUsername());
			foundUser.setPassword(user.getPassword());

			this.userRepository.save(foundUser);
			return foundUser;
		} else {
			return null;
		}
	}

	@DeleteMapping({ "/userId" })
	public void deleteOneUser(Integer userId) {
		this.userRepository.deleteById(userId);
	}

}
