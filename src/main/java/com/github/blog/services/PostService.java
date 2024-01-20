package com.github.blog.services;

import org.springframework.stereotype.Service;

import com.github.blog.requests.SearchPostsRequest;

@Service
public class PostService 
{
    public String search(SearchPostsRequest request) 
    {
        return "Hi";
    }
}
