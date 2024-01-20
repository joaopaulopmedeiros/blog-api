package com.github.blog.services;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.github.blog.repositories.PostRepository;
import com.github.blog.requests.SearchPostRequest;
import com.github.blog.responses.PagedResponse;
import com.github.blog.responses.PostResponse;

@Service
public class PostService 
{
    @Autowired
    private PostRepository repository;

    @Autowired
    private ModelMapper mapper;

    public PagedResponse<PostResponse> search(SearchPostRequest request) 
    {
        var page = PageRequest.of(request.getPage()-1, request.getSize());

        var posts = repository.findAll(page);

        var items = posts
            .stream()
            .map(post -> mapper.map(post, PostResponse.class))
            .collect(Collectors.toList());

        return PagedResponse.<PostResponse>builder()
            .items(items)
            .page(request.getPage())
            .size(request.getSize())
            .build();
    }
}
