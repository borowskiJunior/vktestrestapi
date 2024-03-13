package com.example.api.controller;

import com.example.api.model.Comment;
import com.example.api.model.Post;
import com.example.api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

/**
 * @author Max Borowski
 */

@RestController
@RequestMapping("/vktest")
public class PostController {
    @Autowired
    private PostService postService;

    @Cacheable("posts")
    @GetMapping("/posts")
    public Post[] getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/posts/{id}")
    public Post getPost(@PathVariable("id") int id) {
        return postService.getPost(id);
    }

    @GetMapping("/posts/{id}/comments")
    public Comment[] getPostComments(@PathVariable("id") int id) {
        return postService.getPostComments(id);
    }

    @GetMapping("/comments?postId={id}")
    public Post[] getPostsFromComments(@PathVariable("id") int id){
        return postService.getPostsFromComments(id);
    }

    @PostMapping("/posts")
    public Post create(@RequestBody Post post) {
        return postService.create(post);
    }

    @PutMapping("/posts/{id}")
    public Post updatePut(@RequestBody Post post,
                          @PathVariable("id") int id) {
        return postService.updatePut(post, id);
    }

    @PatchMapping("/posts/{id}")
    public Post patch(@RequestBody Post post,
                      @PathVariable("id") int id) {
        return postService.updatePatch(post, id);
    }

    @DeleteMapping("/posts/{id}")
    public void delete(@PathVariable("id") int id){
        postService.delete(id);
    }
}
