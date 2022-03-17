package com.project.questapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.questapp.entities.Post;
import com.project.questapp.requests.PostRequestDto;
import com.project.questapp.services.PostService;

@RestController
@RequestMapping({ "/posts" })
public class PostController {

	private PostService postService;

	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}

	// @RequestParam bize gelen request içerisindeki parametreleri pars edip ve
	// sağında bulunan değişkenin içerisine atar.
	@GetMapping
	public List<Post> getAllPosts(@RequestParam Optional<Integer> userId) {
		return this.postService.getAllPosts(userId);
	}

	@GetMapping("{/postId}")
	public Post getOnePost(@PathVariable Integer postId) {
		return this.postService.getOnePostById(postId);
	}

	@PostMapping
	public Post createOnePost(@PathVariable PostRequestDto reqDto) {
		return this.postService.creatOnePost(reqDto);
	}

	@PutMapping("/{postId}")
	public Post updateOnePost(@PathVariable Integer postId, @RequestBody PostRequestDto reqDto) {
		return this.postService.updateOnePost(postId, reqDto);
	}

	@DeleteMapping("/{postId}")
	public void deleteOnePost(@PathVariable Integer postId) {
		this.postService.deleteOnePost(postId);
	}
}
