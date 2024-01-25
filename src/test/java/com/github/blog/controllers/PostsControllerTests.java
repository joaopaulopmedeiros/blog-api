package com.github.blog.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import com.github.blog.responses.PagedResponse;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PostsControllerTests 
{
    @LocalServerPort
    private int port;
    
    @Autowired
    private TestRestTemplate template;

    private String baseUrl;

    @BeforeEach
    void setUp() 
    {
        baseUrl = "http://localhost:" + port;
    }

    @Test
    void MustReturnOkWhenSearchWithPagination() throws Exception 
    {
        var result = this.template.getForEntity(baseUrl + "/posts?page=1&size=10", PagedResponse.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

    @Test
    void MustReturnOkWhenSearchWithFilter() throws Exception 
    {
        var result = this.template.getForEntity(baseUrl + "/posts?title=example&page=1&size=10", PagedResponse.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

}
