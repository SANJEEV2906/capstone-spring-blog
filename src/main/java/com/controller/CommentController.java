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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	
	

	@Autowired
    private CommentService commentService;
	
	@PostMapping
    public ResponseEntity<CommentDto> addComment(@Valid @RequestBody CommentDto commentDto) throws ResourceNotFoundException {
        CommentDto createdComment = commentService.addComment(commentDto.getBlogId(), commentDto);
        return ResponseEntity.ok(createdComment);
    }
	
	@GetMapping("/blog/{blogId}")
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
    public ResponseEntity<String> deleteComment(@PathVariable Long id) throws ResourceNotFoundException {
        String response = commentService.deleteComment(id);
        return ResponseEntity.ok(response);
    }
}
