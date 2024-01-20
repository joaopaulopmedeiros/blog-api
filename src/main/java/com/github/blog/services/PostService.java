package com.github.blog.services;

import java.util.List;

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
        PageRequest page = PageRequest.of(request.getPage() - 1, request.getSize());

        List<PostResponse> items = repository.findAll(page)
            .stream()
            .map(post -> mapper.map(post, PostResponse.class))
            .toList();

        return PagedResponse.<PostResponse>builder()
            .items(items)
            .page(request.getPage())
            .size(request.getSize())
            .build();
    }
}
