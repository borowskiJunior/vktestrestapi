package com.example.api.controller;

import com.example.api.model.Post;
import com.example.api.service.RedirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Max Borowski
 */

@RestController
@RequestMapping("/vktest")
public class RedirectController {

    @Autowired
    private RedirectService redirectService;

    @GetMapping("/posts")
    public Post[] getPosts() {

        return redirectService.getPosts();
    }

    @PostMapping("/posts")
    public Post create(@RequestBody Post post){
        return redirectService.createPostWithObject(post);
    }
}
