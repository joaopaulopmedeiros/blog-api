package com.github.blog.services;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.github.blog.models.Post;
import com.github.blog.repositories.PostRepository;
import com.github.blog.requests.SearchPostRequest;
import com.github.blog.requests.StorePostRequest;
import com.github.blog.responses.PagedResponse;
import com.github.blog.responses.PostResponse;
import com.github.blog.specifications.PostSpecification;

@Service
public class PostService 
{
    private final PostRepository repository;
    private final ModelMapper mapper;

    public PostService(PostRepository repository, ModelMapper mapper)
    {
        this.repository = repository;
        this.mapper = mapper;
    }

    public PagedResponse<PostResponse> search(SearchPostRequest request) 
    {
        PageRequest page = PageRequest.of(request.getPage() - 1, request.getSize());

        Specification<Post> specification = new PostSpecification().fromRequest(request);

        List<PostResponse> items = repository.findAll(specification, page)
            .stream()
            .map(post -> mapper.map(post, PostResponse.class))
            .toList();

        return PagedResponse.<PostResponse>builder()
            .items(items)
            .page(request.getPage())
            .size(request.getSize())
            .build();
    }

    public PostResponse store(StorePostRequest request)
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
