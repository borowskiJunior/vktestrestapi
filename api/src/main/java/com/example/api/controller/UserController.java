package com.example.api.controller;

import com.example.api.model.User;
import com.example.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

/**
 * @author Max Borowski
 */
@RestController
@RequestMapping("/vktest")
public class UserController {
    @Autowired
    private UserService userService;

    @Cacheable("users")
    @GetMapping("/users")
    public User[] getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") int id) {
        return userService.getUser(id);
    }

    @PostMapping("/users")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping("/users/{id}")
    public User updatePut(@RequestBody User user,
                          @PathVariable("id") int id) {
        return userService.updatePut(user, id);
    }

    @PatchMapping("/users/{id}")
    public User patch(@RequestBody User user,
                      @PathVariable("id") int id) {
        return userService.updatePatch(user, id);
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable("id") int id) {
        userService.delete(id);
    }
}
