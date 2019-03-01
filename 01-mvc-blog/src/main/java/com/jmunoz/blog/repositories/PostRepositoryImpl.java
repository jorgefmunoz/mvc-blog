package com.jmunoz.blog.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.jmunoz.blog.models.Post;

@Repository
public class PostRepositoryImpl implements PostRepository {

	// define field for entitymanager
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Post> findLatest5Posts() {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create a query
		// “SELECT p FROM Post p LEFT JOIN FETCH p.author ORDER BY p.date DESC”
		Query<Post>	theQuery = currentSession.createQuery("select p from Post p"
															+ " left join fetch p.author"
															+ " order by p.date desc", Post.class);
		theQuery.setMaxResults(5);
		
		// execute query and get result list
		List<Post> latest5Posts = theQuery.getResultList();
		
		// return the results		
		return latest5Posts;
	}

	@Override
	public List<Post> findAll() {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create a query
		Query<Post> theQuery = currentSession.createQuery("from Post", Post.class);
		
		// execute query and get result list
		List<Post> posts = theQuery.getResultList();
		
		// return the results
		return posts;
	}

	@Override
	public Post findbyId(Long id) {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// get the post
		Post thePost = currentSession.get(Post.class, id);
		
		// return the post
		return thePost;
	}

	@Override
	public void save(Post post) {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// save post
		currentSession.saveOrUpdate(post);
	}

	@Override
	public void deleteById(Long id) {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// delete object with primary key
		Query theQuery = currentSession.createQuery("delete from Post where id=:postId");
		theQuery.setParameter("postId", id);
		
		theQuery.executeUpdate();		
	}

}
