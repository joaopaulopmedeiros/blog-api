package com.github.blog.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatusCode;

import com.github.blog.responses.PagedResponse;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PostsControllerTests 
{
    @LocalServerPort
    private int port;
    
    @Autowired
    private TestRestTemplate template;

    @Test
    void MustReturnPaginatedPosts() throws Exception 
    {
        var result = this.template.getForEntity("http://localhost:" + port + "/posts?page=1&size=10", PagedResponse.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
	}
}
