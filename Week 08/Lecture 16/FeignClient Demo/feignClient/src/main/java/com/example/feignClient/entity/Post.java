package com.example.feignClient.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {
    private Long userId;
    private Long id;
    private String title;
    private String body;
}