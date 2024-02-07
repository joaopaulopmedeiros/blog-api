package com.github.blog.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.github.blog.requests.SearchPostRequest;
import com.github.blog.requests.StorePostRequest;
import com.github.blog.responses.PagedResponse;
import com.github.blog.responses.PostResponse;
import com.github.blog.services.SearchPostService;
import com.github.blog.services.StorePostService;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value="posts")
public class PostsController 
{
    private final SearchPostService search;
    private final StorePostService store;

    public PostsController(SearchPostService search, StorePostService store)
    {
        this.search = search;
        this.store = store;
    }

    @GetMapping
    @Cacheable("posts")
    public ResponseEntity<PagedResponse<PostResponse>> search(SearchPostRequest request)
    {
        PagedResponse<PostResponse> result = search.run(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @CacheEvict(cacheNames="posts", allEntries=true)
    public ResponseEntity<PostResponse> store(@RequestBody StorePostRequest request) {        
        PostResponse result = store.run(request);
        return ResponseEntity.ok(result);
    }    
}
