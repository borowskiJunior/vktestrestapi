package com.example.api.service;

import com.example.api.model.Album;
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
public class AlbumService {
    private final RestTemplate restTemplate;

    public AlbumService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Album[] getAlbums() {
        String url = "https://jsonplaceholder.typicode.com/albums";
        return this.restTemplate.getForObject(url, Album[].class);
    }

    public Album getAlbum(int id) {
        String url = "https://jsonplaceholder.typicode.com/albums" + "/" + String.valueOf(id);
        return this.restTemplate.getForObject(url, Album.class);
    }


    public Album create(Album album) {
        String url = "https://jsonplaceholder.typicode.com/albums";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Album> entity = new HttpEntity<>(album, headers);

        return restTemplate.postForObject(url, entity, Album.class);
    }

    public Album updatePut(Album album, int id) {
        String url = "https://jsonplaceholder.typicode.com/albums/{id}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Album> entity = new HttpEntity<>(album, headers);

        ResponseEntity<Album> response = this.restTemplate.exchange(url, HttpMethod.PUT, entity, Album.class, id);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
        }
    }


    public Album updatePatch(Album album, int id) {
        String url = "https://jsonplaceholder.typicode.com/albums/" + String.valueOf(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Album> entity = new HttpEntity<>(album, headers);
        return this.restTemplate.patchForObject(url, entity, Album.class);
    }


    public void delete(int id){
        String url = "https://jsonplaceholder.typicode.com/albums/{id}";

        this.restTemplate.delete(url, id);
    }
}
