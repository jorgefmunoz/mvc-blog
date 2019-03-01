package com.jmunoz.blog.repositories;

import java.util.List;

import com.jmunoz.blog.models.Post;

public interface PostRepository {
	
	List<Post> findAll();
	
	List<Post> findLatest5Posts();
	
	Post findbyId(Long id);
	
	void save(Post post);
	
	void deleteById(Long id);

}
