package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.BlogDto;
import com.entity.Blog;
import com.exception.ResourceNotFoundException;
import com.service.BlogService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;
    
    
    //To create a new Blog
    @PostMapping
    public ResponseEntity<BlogDto> createBlog(@Valid @RequestBody BlogDto blogDto) {
        return ResponseEntity.status(201).body(blogService.createBlog(blogDto));
    }
    
    //To get the Blog based on given Id
    @GetMapping("/{id}")
    public ResponseEntity<BlogDto> getBlogById(@PathVariable Long id) throws ResourceNotFoundException{ //throws ResourceNotFoundException {
        return ResponseEntity.ok(blogService.getBlogById(id));
    }
    
    //Update the blog based on id
    @PutMapping("/{id}")
    public ResponseEntity<BlogDto> updateBlog(@PathVariable Long id, @Valid @RequestBody BlogDto blogDto) throws ResourceNotFoundException {
        return ResponseEntity.ok(blogService.updateBlog(id, blogDto));
    }
    
    //Delete the blog based on Id
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteBlog(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(blogService.deleteBlog(id));
    }
    
    //To get all the blogs
    @GetMapping("/all")
    public ResponseEntity<List<Blog>> getAllBlogs() {
        List<Blog> blogs = blogService.findAll();
        return ResponseEntity.ok(blogs);
    }
}
