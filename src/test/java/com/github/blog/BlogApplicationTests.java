package com.github.blog;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.blog.controllers.PostsController;

@SpringBootTest
class BlogApplicationTests {

	@Autowired
	private PostsController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();	
	}

}
