package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.CommentDto;
import com.exception.ResourceNotFoundException;
import com.service.CommentService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/blogs/comment")
public class CommentController {
	
	@Autowired
    private CommentService commentService;
	
	/**
	 * Adds a new comment to a blog post.
	 * @param commentDto The comment details.
	 * @return The created comment.
	 * @throws ResourceNotFoundException if the blog post is not found.
	 */
	
	@PostMapping
	@Tag(name="Add the Comment")
    public ResponseEntity<CommentDto> addComment(@Valid @RequestBody CommentDto commentDto) throws ResourceNotFoundException {
        CommentDto createdComment = commentService.addComment(commentDto.getBlogId(), commentDto);
        return ResponseEntity.ok(createdComment);
    }
	
	/**
	 * Retrieves all comments for a specific blog post.
	 * @param blogId The ID of the blog post.
	 * @return A list of comments for the given blog.
	 * @throws ResourceNotFoundException if no comments are found for the blog ID.
	 */
	@GetMapping("/blog/{blogId}")
	@Tag(name="Get comment based on the blog id")
    public ResponseEntity<List<CommentDto>> getCommentsByBlogId(@PathVariable Long blogId) throws ResourceNotFoundException {
        return ResponseEntity.ok(commentService.getCommentsByBlogId(blogId));
    }
	
	/**
     * Deletes a comment by its ID.
     * @param id The comment ID.
     * @return A confirmation message.
     * @throws ResourceNotFoundException if the comment is not found.
     */
    @DeleteMapping("/{id}")
    @Tag(name="Delete a comment by its id")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) throws ResourceNotFoundException {
        String response = commentService.deleteComment(id);
        return ResponseEntity.ok(response);
    }
}
