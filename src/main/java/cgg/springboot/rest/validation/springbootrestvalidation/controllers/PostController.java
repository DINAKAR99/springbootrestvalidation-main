package cgg.springboot.rest.validation.springbootrestvalidation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cgg.springboot.rest.validation.springbootrestvalidation.dto.PostDto;
import cgg.springboot.rest.validation.springbootrestvalidation.services.PostService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class PostController {
	
	@Autowired
	private PostService postService;
	//create
	@PostMapping("/user/{userid}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId)
			{
		PostDto createPost=this.postService.createPost(postDto,userId,categoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
			}
	
	//get by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
	List<PostDto> posts=this.postService.getPostsByUser(userId);
	return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	//get by category
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
	List<PostDto> posts=this.postService.getPostsByCategory(categoryId);
	return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	//get all posts
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPost(){
	List<PostDto>allPost=this.postService.getAllPost();
	return new ResponseEntity<List<PostDto>>(allPost,HttpStatus.OK);
	}
	
	//get post details by id
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
	{
	PostDto postDto=this.postService.getPostById(postId);
	return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}

}
