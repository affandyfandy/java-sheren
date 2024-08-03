package com.example.feignClient.client;

import com.example.feignClient.config.ClientConfiguration;
import com.example.feignClient.hystrix.JSONPlaceholderFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.feignClient.entity.Post;

import java.util.List;

@FeignClient(value = "jplaceholder",
        url = "https://jsonplaceholder.typicode.com/",
        configuration = ClientConfiguration.class,
        fallback = JSONPlaceholderFallback.class)
public interface JSONPlaceholderClient {

    @RequestMapping(method = RequestMethod.GET, value = "/posts")
    List<Post> getPosts();

    @RequestMapping(method = RequestMethod.GET, value = "/posts/{postId}", produces = "application/json")
    Post getPostById(@PathVariable("postId") Long postId);

}
