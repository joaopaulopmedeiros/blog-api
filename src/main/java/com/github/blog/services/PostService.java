package com.github.blog.services;

import org.springframework.stereotype.Service;

import com.github.blog.requests.SearchPostRequest;
import com.github.blog.responses.PagedResponse;
import com.github.blog.responses.PostResponse;

@Service
public class PostService 
{
    public PagedResponse<PostResponse> search(SearchPostRequest request) 
    {
        return PagedResponse.<PostResponse>builder()
            .items(null)
            .page(request.getPage())
            .size(request.getSize())
            .build();
    }
}
