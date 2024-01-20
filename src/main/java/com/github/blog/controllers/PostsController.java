package com.github.blog.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.github.blog.requests.SearchPostRequest;
import com.github.blog.requests.StorePostRequest;
import com.github.blog.responses.PagedResponse;
import com.github.blog.responses.PostResponse;
import com.github.blog.services.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value="posts")
public class PostsController 
{
    @Autowired
    private PostService service;

    @GetMapping
    public ResponseEntity<PagedResponse<PostResponse>> search(SearchPostRequest request)
    {
        PagedResponse<PostResponse> result = service.search(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<PostResponse> store(@RequestBody StorePostRequest request) {        
        PostResponse result = service.store(request);
        return ResponseEntity.ok(result);
    }    
}
