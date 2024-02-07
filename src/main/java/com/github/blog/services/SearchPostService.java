package com.github.blog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.github.blog.events.analytics.SearchAnalyticsEventProducer;
import com.github.blog.events.analytics.SearchAnalyticsData;
import com.github.blog.events.analytics.SearchAnalyticsEvent;
import com.github.blog.models.Post;
import com.github.blog.repositories.PostRepository;
import com.github.blog.requests.SearchPostRequest;
import com.github.blog.responses.PagedResponse;
import com.github.blog.responses.PostResponse;
import com.github.blog.specifications.PostSpecification;

@Service
public class SearchPostService 
{
    private final PostRepository repository;
    private final SearchAnalyticsEventProducer producer;
    private final ModelMapper mapper;

    public SearchPostService(PostRepository repository, SearchAnalyticsEventProducer producer, ModelMapper mapper)
    {
        this.repository = repository;
        this.producer = producer;
        this.mapper = mapper;
    }

    public PagedResponse<PostResponse> run(SearchPostRequest request) 
    {
        List<PostResponse> items = queryPosts(request);

        this.producer.send(raiseSearchEvent(items));

        return PagedResponse.<PostResponse>builder()
            .items(items)
            .page(request.getPage())
            .size(request.getSize())
            .build();
    }

    private SearchAnalyticsEvent raiseSearchEvent(List<PostResponse> items) 
    {
        var data = SearchAnalyticsData.builder().postIds(items.stream()
        .map(PostResponse::getId)
        .collect(Collectors.toList()))
        .build();

       return new SearchAnalyticsEvent(data);
    }

    private List<PostResponse> queryPosts(SearchPostRequest request) 
    {
        PageRequest page = PageRequest.of(request.getPage() - 1, request.getSize());

        Specification<Post> specification = new PostSpecification().fromRequest(request);

        Page<Post> registries = specification != null 
            ? repository.findAll(specification, page) 
            : repository.findAll(page);

        return registries
            .stream()
            .map(post -> mapper.map(post, PostResponse.class))
            .toList();
    }
}
