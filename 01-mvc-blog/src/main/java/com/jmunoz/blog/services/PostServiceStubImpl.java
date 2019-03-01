package com.jmunoz.blog.services;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jmunoz.blog.models.Post;
import com.jmunoz.blog.repositories.PostRepository;

@Service
public class PostServiceStubImpl implements PostService {
	
//	private List<Post> posts = new ArrayList() {{
//		add(new Post(1L, "First Post", "<p>Line #1.</p><p>Line #2</p>",null));
//		add(new Post(2L, "Second Post", "Second post content:<ul><li>line 1</li><li>line 2</li></p>",
//				new User(10L, "pesho10", "Peter Ivanov")));
//		add(new Post(3L, "Post #3", "<p>The post number 3 nice</p>",
//				new User(10L, "merry", null)));
//        add(new Post(4L, "Forth Post", "<p>Not interesting post</p>", null));
//        add(new Post(5L, "Post Number 5", "<p>Just posting</p>", null));
//        add(new Post(6L, "Sixth Post", "<p>Another interesting post</p>", null));
//	}};
	
	@Autowired
	private PostRepository postRepo;

	@Override
	@Transactional
	public List<Post> findAll() {
		return this.postRepo.findAll();
	}

	@Override
	@Transactional
	public List<Post> findLatest5() {
//		return this.posts.stream()
//					.sorted((a,b) -> b.getDate().compareTo(a.getDate()))
//					.limit(5)
//					.collect(Collectors.toList());
		return this.postRepo.findLatest5Posts();
	}

	@Override
	@Transactional
	public Post findbyId(Long id) {
//		return this.posts.stream()
//					.filter(p -> Objects.equals(p.getId(), id))
//					.findFirst()
//					.orElse(null);
		return this.postRepo.findbyId(id);
	}

	@Override
	@Transactional
	public void create(Post post) {
		this.postRepo.save(post);
	}

	@Override
	@Transactional
	public void edit(Post post) {
//       for (int i = 0; i < this.posts.size(); i++) {
//            if (Objects.equals(this.posts.get(i).getId(), post.getId())) {
//                this.posts.set(i, post);
//                return post;
//            }
//        }
//        throw new RuntimeException("Post not found: " + post.getId());
		this.postRepo.save(post);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
//        for (int i = 0; i < this.posts.size(); i++) {
//            if (Objects.equals(this.posts.get(i).getId(), id)) {
//                this.posts.remove(i);
//            }
//        }
//        throw new RuntimeException("Post not found: " + id);
		this.postRepo.deleteById(id);
	}

}
