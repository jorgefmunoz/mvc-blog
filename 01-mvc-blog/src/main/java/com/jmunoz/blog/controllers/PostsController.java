package com.jmunoz.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jmunoz.blog.models.Post;
import com.jmunoz.blog.services.NotificationService;
import com.jmunoz.blog.services.PostService;

@Controller
public class PostsController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private NotificationService notifyService;
	
	@RequestMapping("/posts/view/{id}")
	public String view(@PathVariable("id") Long id, Model model) {
		Post post = postService.findbyId(id);
		if (post == null) {
			notifyService.addErrorMessage("Cannot find post #" + id);
			return "redirect:/";
		}
		model.addAttribute("post", post);
		return "posts/view";
	}
}
