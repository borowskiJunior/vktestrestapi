package com.example.api.service;

import com.example.api.model.User;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * @author Max Borowski
 */
@Service
public class UserService {
    private final RestTemplate restTemplate;

    public UserService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public User[] getUsers() {
        String url = "https://jsonplaceholder.typicode.com/users";
        return this.restTemplate.getForObject(url, User[].class);
    }

    public User getUser(int id) {
        String url = "https://jsonplaceholder.typicode.com/users" + "/" + String.valueOf(id);
        return this.restTemplate.getForObject(url, User.class);
    }

    public User create(User user) {
        String url = "https://jsonplaceholder.typicode.com/users";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        return restTemplate.postForObject(url, entity, User.class);
    }

    public User updatePut(User user, int id) {
        String url = "https://jsonplaceholder.typicode.com/users/{id}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        ResponseEntity<User> response = this.restTemplate.exchange(url, HttpMethod.PUT, entity, User.class, id);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
        }
    }


    public User updatePatch(User user, int id) {
        String url = "https://jsonplaceholder.typicode.com/users/" + String.valueOf(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        return this.restTemplate.patchForObject(url, entity, User.class);
    }


    public void delete(int id){
        String url = "https://jsonplaceholder.typicode.com/users/{id}";

        this.restTemplate.delete(url, id);
    }

}
