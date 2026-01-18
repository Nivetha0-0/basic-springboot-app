package com.project.jobposts.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.jobposts.model.Jobpostings;
import com.project.jobposts.repository.PostRepository;
import com.project.jobposts.repository.SearchRepository;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class PostController {

    @Autowired
    PostRepository repo;

    @Autowired
    SearchRepository srepo;

    @RequestMapping("/")
    public void redirectToSwagger(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui/index.html");
    }

    @GetMapping("/posts")
    public List<Jobpostings> getAllPosts() {
        return repo.findAll();
    }


    @GetMapping("/posts/{text}")
    public List<Jobpostings> search(@PathVariable String text){
        return srepo.findByText(text);
    }

    @PostMapping("/addingPost")
    public Jobpostings addPost(@RequestBody Jobpostings post) {
        return repo.save(post);
    }
}
