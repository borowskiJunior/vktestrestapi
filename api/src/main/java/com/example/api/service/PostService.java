package com.example.api.service;

import com.example.api.model.Comment;
import com.example.api.model.Post;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * @author Max Borowski
 */

@Service
public class PostService {
    private final RestTemplate restTemplate;

    public PostService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Post[] getPosts() {
        String url = "https://jsonplaceholder.typicode.com/posts";
        return this.restTemplate.getForObject(url, Post[].class);
    }
    public Post getPost(int id) {
        String url = "https://jsonplaceholder.typicode.com/posts" + "/" + String.valueOf(id);
        return this.restTemplate.getForObject(url, Post.class);
    }

    public Comment[] getPostComments(int id) {
        String url = "https://jsonplaceholder.typicode.com/posts/" + String.valueOf(id) + "/comments";
        return this.restTemplate.getForObject(url, Comment[].class);
    }

    public Post[] getPostsFromComments(@PathVariable("id") int id){
        String url = "https://jsonplaceholder.typicode.com/comments/?postId=" + String.valueOf(id);
        return this.restTemplate.getForObject(url, Post[].class);
    }
    public Post create(Post post) {
        String url = "https://jsonplaceholder.typicode.com/posts";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Post> entity = new HttpEntity<>(post, headers);

        return restTemplate.postForObject(url, entity, Post.class);
    }

    public Post updatePut(Post post, int id) {
        String url = "https://jsonplaceholder.typicode.com/posts/{id}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Post> entity = new HttpEntity<>(post, headers);

        ResponseEntity<Post> response = this.restTemplate.exchange(url, HttpMethod.PUT, entity, Post.class, id);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
        }
    }

    public Post updatePatch(Post post, int id) {
        String url = "https://jsonplaceholder.typicode.com/posts/" + String.valueOf(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Post> entity = new HttpEntity<>(post, headers);
        return this.restTemplate.patchForObject(url, entity, Post.class);
    }

    public void delete(int id){
        String url = "https://jsonplaceholder.typicode.com/posts/{id}";

        this.restTemplate.delete(url, id);
    }
}