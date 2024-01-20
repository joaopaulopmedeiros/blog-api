package com.github.blog.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagedRequest 
{
    private Integer page;
    private Integer size;    
}
