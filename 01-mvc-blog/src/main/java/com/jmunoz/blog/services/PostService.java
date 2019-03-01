package com.jmunoz.blog.services;

import java.util.List;

import com.jmunoz.blog.models.Post;

public interface PostService {
	
	List<Post> findAll();
	
	List<Post> findLatest5();
	
	Post findbyId(Long id);
	
	void create(Post post);
	
	void edit(Post post);
	
	void deleteById(Long id);

}
