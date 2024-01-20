package com.github.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.blog.models.Post;

public interface PostRepository extends JpaRepository<Post, Long> 
{
}