package com.github.blog.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(value="posts")
public class PostsController 
{
    @GetMapping
    public String helloWorld()
    {
        return "Hello World";
    }
}
