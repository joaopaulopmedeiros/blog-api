package com.github.blog.events;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseEvent<T>
{
    protected String type;
    protected LocalDateTime timestamp = LocalDateTime.now();    
    protected T data;
}
