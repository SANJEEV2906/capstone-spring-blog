package com.service;

import com.dto.BlogDto;

import com.entity.Blog;

import com.exception.ResourceNotFoundException;
import com.repository.BlogRepository;
import com.repository.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing blog operations.
 */
@Service
public class BlogService {
    
	@Autowired
    private BlogRepository blogRepository;
    
    /*public BlogService(BlogRepository blogRepository) {
    	this.blogRepository=blogRepository;
    }*/

    public BlogDto createBlog(BlogDto blogDto) {
        Blog blog = new Blog();
        blog.setTitle(blogDto.getTitle());
        blog.setContent(blogDto.getContent());

        Blog savedBlog = blogRepository.save(blog);
        blogDto.setId(savedBlog.getId());
        return blogDto;
    }

    public BlogDto getBlogById(Long id) throws ResourceNotFoundException {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found"));
        
        BlogDto blogDto = new BlogDto();
        blogDto.setId(blog.getId());
        blogDto.setTitle(blog.getTitle());
        blogDto.setContent(blog.getContent());
        return blogDto;
    }

    public BlogDto updateBlog(Long id, BlogDto blogDto) throws ResourceNotFoundException {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found"));

        blog.setTitle(blogDto.getTitle());
        blog.setContent(blogDto.getContent());

        Blog updatedBlog = blogRepository.save(blog);
        blogDto.setId(updatedBlog.getId());
        return blogDto;
    }

    public String deleteBlog(Long id) throws ResourceNotFoundException {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found"));

        blogRepository.delete(blog);
        return "Blog has been Deleted";
    }
    
    
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }
}
