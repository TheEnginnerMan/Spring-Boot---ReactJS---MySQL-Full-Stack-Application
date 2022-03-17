package com.project.questapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.questapp.entities.User;
import com.project.questapp.repository.UserRepository;

@Service
public class UserService {

	public UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> getAllUsers() {

		return this.userRepository.findAll();
	}

	public User createUser(User user) {
		return this.userRepository.save(user);
	}

	public User getOneUser(Integer userId) {
		return this.userRepository.findById(userId).orElse(null); // user database de olmazsa null dönecek
	}

	public User updateOneUser(Integer userId, User user) {
		Optional<User> user1 = this.userRepository.findById(userId); // update edilmeden önce userIdsi belirtilen useri
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

	public void deleteOneUser(Integer userId) {
		this.userRepository.deleteById(userId);
	}

}
