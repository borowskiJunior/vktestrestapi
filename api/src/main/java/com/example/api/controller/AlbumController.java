package com.example.api.controller;

import com.example.api.model.Album;
import com.example.api.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Max Borowski
 */

@RestController
@RequestMapping("/vktest")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping("/albums")
    public Album[] getAlbums() {
        return albumService.getAlbums();
    }

    @GetMapping("/albums/{id}")
    public Album getPost(@PathVariable("id") int id) {
        return albumService.getAlbum(id);
    }

    @PostMapping("/albums")
    public Album create(@RequestBody Album album) {
        return albumService.create(album);
    }

    @PutMapping("/albums/{id}")
    public Album updatePut(@RequestBody Album album,
                          @PathVariable("id") int id) {
        return albumService.updatePut(album, id);
    }

    @PatchMapping("/albums/{id}")
    public Album patch(@RequestBody Album album,
                      @PathVariable("id") int id) {
        return albumService.updatePatch(album, id);
    }

    @DeleteMapping("/albums/{id}")
    public void delete(@PathVariable("id") int id){
        albumService.delete(id);
    }
}
