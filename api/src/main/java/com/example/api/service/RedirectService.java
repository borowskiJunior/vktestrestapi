package com.example.api.service;

import com.example.api.model.Post;
import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * @author Max Borowski
 */

@Service
public class RedirectService {

    private final RestTemplate restTemplate;

    public RedirectService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Post[] getPosts() {
        String url = "https://jsonplaceholder.typicode.com/posts";
        return this.restTemplate.getForObject(url, Post[].class);
    }

    public Post createPostWithObject(Post post) {
        String url = "https://jsonplaceholder.typicode.com/posts";

        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // build the request
        HttpEntity<Post> entity = new HttpEntity<>(post, headers);

        // send POST request
        return restTemplate.postForObject(url, entity, Post.class);
    }

}