package com.example.feignClient.service;

import com.example.feignClient.entity.Post;

import java.util.List;

public interface PostService {

    List<Post> getPosts();

    Post getPostById(Long id);
}
