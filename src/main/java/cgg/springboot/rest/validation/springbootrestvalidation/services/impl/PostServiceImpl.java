package cgg.springboot.rest.validation.springbootrestvalidation.services.impl;



import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import cgg.springboot.rest.validation.springbootrestvalidation.dao.CategoryRepo;
import cgg.springboot.rest.validation.springbootrestvalidation.dao.PostRepo;
import cgg.springboot.rest.validation.springbootrestvalidation.dao.UserRepository;
import cgg.springboot.rest.validation.springbootrestvalidation.dto.PostDto;
import cgg.springboot.rest.validation.springbootrestvalidation.entities.Category;
import cgg.springboot.rest.validation.springbootrestvalidation.entities.Post;
import cgg.springboot.rest.validation.springbootrestvalidation.entities.User1;
import cgg.springboot.rest.validation.springbootrestvalidation.exceptions.ResourceNotFoundException;
import cgg.springboot.rest.validation.springbootrestvalidation.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		User1 user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User id",userId));
		Category category =this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category id",categoryId));

				Post post=this.modelMapper.map(postDto,Post.class);
				post.setImageName("default.png");
				post.setAddedDate(new Date());
				post.setUser(user);
				post.setCategory(category);
				Post newPost=this.postRepo.save(post);
				return this.modelMapper.map(newPost,PostDto.class);
	}

	@Override
	public Post updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PostDto> getAllPost() {
		List<Post> allPosts=this.postRepo.findAll();
		List<PostDto> postDtos=allPosts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post=this.postRepo.findById(postId)
		.orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
		return this.modelMapper.map(post,PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category id",categoryId));
		List<Post> posts=this.postRepo.findByCategory(cat);
		List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User1 user =this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
		List<Post> posts  =this.postRepo.findByUser(user);
		List<PostDto> postDtos= posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<Post> searchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
