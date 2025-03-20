package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.BlogDto;
import com.entity.Blog;
import com.exception.InvalidIdException;
import com.exception.ResourceNotFoundException;
import com.repository.BlogRepository;

/**
 * Service class for managing blog operations.
 */
@Service  //Marks a class as a service component.
public class BlogService {
    
	@Autowired
    private BlogRepository blogRepository;
    
    /*public BlogService(BlogRepository blogRepository) {
    	this.blogRepository=blogRepository;
    }*/

	/**
     * Creates a new blog post.
     * @param blogDto The blog details.
     * @return The created blog with its assigned ID.
     */
    public BlogDto createBlog(BlogDto blogDto) {
        Blog blog = new Blog();
        blog.setTitle(blogDto.getTitle());
        blog.setContent(blogDto.getContent());

        Blog savedBlog = blogRepository.save(blog);
        blogDto.setId(savedBlog.getId());
        return blogDto;
    }
    
    /**
     * Retrieves a blog post by its ID.
     * @param id The ID of the blog post.
     * @return The blog details.
     * @throws ResourceNotFoundException if the blog is not found.
     */
    public BlogDto getBlogById(Long id) throws ResourceNotFoundException {
    	
    	if (id <= 0) {
            throw new InvalidIdException("ID must be positive");
        }
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
    
    /**
     * Updates an existing blog post.
     * @param id The ID of the blog post to update.
     * @param blogDto The new blog details.
     * @return The updated blog details.
     * @throws ResourceNotFoundException if the blog is not found.
     */
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
