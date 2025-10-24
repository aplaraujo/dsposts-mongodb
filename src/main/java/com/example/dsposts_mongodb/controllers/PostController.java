package com.example.dsposts_mongodb.controllers;

import com.example.dsposts_mongodb.models.dto.PostDTO;
import com.example.dsposts_mongodb.models.dto.UserDTO;
import com.example.dsposts_mongodb.services.PostService;
import com.example.dsposts_mongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostController {
    @Autowired
    private PostService postService;


    @GetMapping(value = "/{id}")
    public ResponseEntity<PostDTO> findById(@PathVariable String id) {
        PostDTO obj = postService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<PostDTO>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        List<PostDTO> obj = postService.findByTitle(text);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/fullsearch")
    public ResponseEntity<List<PostDTO>> fullSearch(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "start", defaultValue = "") String start,
            @RequestParam(value = "end", defaultValue = "") String end) {
        List<PostDTO> list = postService.fullSearch(text, start, end);
        return ResponseEntity.ok().body(list);
    }

}
