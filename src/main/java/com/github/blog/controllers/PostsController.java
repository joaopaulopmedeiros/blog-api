package com.github.blog.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.github.blog.requests.SearchPostsRequest;
import com.github.blog.services.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(value="posts")
public class PostsController 
{
    @Autowired
    private PostService service;

    @GetMapping
    public ResponseEntity<?> search(SearchPostsRequest request)
    {
        var result = service.search(request);
        return ResponseEntity.ok(result);
    }
}
