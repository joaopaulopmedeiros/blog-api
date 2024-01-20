package com.github.blog.responses;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostResponse
{
    private Integer id;
    private String title;
    private String author;
    private LocalDateTime publishedAt;    
}
