package com.github.blog.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.github.blog.models.Post;
import com.github.blog.requests.SearchPostRequest;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PostSpecification
{
    public Specification<Post> fromRequest(SearchPostRequest request) 
    {
		return (root, query, builder) -> {

            if (request.getTitle() != null && !request.getTitle().isEmpty()) 
            {
                return builder.like(root.get("title"), "%" + request.getTitle() + "%");
            }

            return null;
        };
    }
}
