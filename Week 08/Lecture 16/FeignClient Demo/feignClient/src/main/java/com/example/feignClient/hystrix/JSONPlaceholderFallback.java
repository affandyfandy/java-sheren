package com.example.feignClient.hystrix;

import com.example.feignClient.client.JSONPlaceholderClient;
import com.example.feignClient.entity.Post;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class JSONPlaceholderFallback implements JSONPlaceholderClient {

    @Override
    public List<Post> getPosts() {
        return Collections.emptyList();
    }

    @Override
    public Post getPostById(Long id) {
        return null;
    }
}
