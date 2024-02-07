package com.github.blog.services;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.github.blog.models.Post;
import com.github.blog.repositories.PostRepository;
import com.github.blog.requests.StorePostRequest;
import com.github.blog.responses.PostResponse;

@Service
public class StorePostService 
{
    private final PostRepository repository;
    private final ModelMapper mapper;

    public StorePostService(PostRepository repository, ModelMapper mapper)
    {
        this.repository = repository;
        this.mapper = mapper;
    }
    
    public PostResponse run(StorePostRequest request)
    {
        Post post = Post
            .builder()
            .title(request.getTitle())
            .publishedAt(LocalDateTime.now())
            .build();
        
        if (post == null) return null;
        
        repository.save(post);
        
        return mapper.map(post, PostResponse.class);
    }
}
