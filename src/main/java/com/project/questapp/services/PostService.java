package com.project.questapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.repository.PostRepository;
import com.project.questapp.requests.PostRequestDto;

@Service
public class PostService {

	private PostRepository postRepository;
	private UserService userService;

	@Autowired
	public PostService(PostRepository postRepository, UserService userService) {
		this.postRepository = postRepository;
		this.userService = userService;
	}

	public List<Post> getAllPosts(Optional<Integer> userId) {
		if (userId.isPresent()) {// user id parametresi geldimi denetlenir.
			return this.postRepository.findByUserId(userId.get());
		}
		return this.postRepository.findAll();
	}

	public Post getOnePostById(Integer postId) {
		return this.postRepository.findById(postId).orElse(null);
	}

	public Post creatOnePost(PostRequestDto reqDto) {
		User user = this.userService.getOneUser(reqDto.getUserId());
		if (user == null)
			return null;

		Post toSave = new Post();
		toSave.setId(reqDto.getId());
		toSave.setText(reqDto.getText());
		toSave.setTitle(reqDto.getTitle());
		toSave.setUser(user);
		return this.postRepository.save(toSave);
	}

	public Post updateOnePost(Integer postId, PostRequestDto reqDto) {
		Optional<Post> post = this.postRepository.findById(postId);
		if (post.isPresent()) {
			Post toUpdate = post.get();
			toUpdate.setText(reqDto.getText());
			toUpdate.setTitle(reqDto.getTitle());
			this.postRepository.save(toUpdate);
			return toUpdate;
		}
		return null;
	}

	public void deleteOnePost(Integer postId) {
		this.postRepository.deleteById(postId);

	}

}
