package com.example.feignClient.service.impl;

import com.example.feignClient.client.JSONPlaceholderClient;
import com.example.feignClient.entity.Post;
import com.example.feignClient.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final JSONPlaceholderClient jsonPlaceholderClient;

    @Autowired
    public PostServiceImpl(JSONPlaceholderClient jsonPlaceholderClient) {
        this.jsonPlaceholderClient = jsonPlaceholderClient;
    }

    @Override
    public List<Post> getPosts() {
        return jsonPlaceholderClient.getPosts();
    }

    @Override
    public Post getPostById(Long id) {
        return jsonPlaceholderClient.getPostById(id);
    }
}
