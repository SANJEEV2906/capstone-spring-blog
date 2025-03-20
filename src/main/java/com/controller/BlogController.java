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

/**
 * REST controller for managing blogs.
 */
@RestController
@RequestMapping("/api/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;
    
    /**
     * Creates a new blog.
     * @param blogDto The blog data.
     * @return The created blog.
     */
    @PostMapping
    public ResponseEntity<BlogDto> createBlog(@Valid @RequestBody BlogDto blogDto) {
        return ResponseEntity.status(201).body(blogService.createBlog(blogDto));
    }
    
    /**
     * Retrieves a blog by its ID.
     * @param id The blog ID.
     * @return The blog details.
     * @throws ResourceNotFoundException if the blog is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<BlogDto> getBlogById(@PathVariable Long id) throws ResourceNotFoundException{ 
        return ResponseEntity.ok(blogService.getBlogById(id));
    }
    
    /**
     * Updates an existing blog.
     * @param id The blog ID.
     * @param blogDto The updated blog data.
     * @return The updated blog.
     * @throws ResourceNotFoundException if the blog is not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<BlogDto> updateBlog(@PathVariable Long id, @Valid @RequestBody BlogDto blogDto) throws ResourceNotFoundException {
        return ResponseEntity.ok(blogService.updateBlog(id, blogDto));
    }
    
    /**
     * Deletes a blog by its ID.
     * @param id The blog ID.
     * @return A confirmation message.
     * @throws ResourceNotFoundException if the blog is not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(blogService.deleteBlog(id));
    }
    
    /**
     * Retrieves all blogs.
     * @return A list of all blogs.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Blog>> getAllBlogs() {
        List<Blog> blogs = blogService.findAll();
        return ResponseEntity.ok(blogs);
    }
}
