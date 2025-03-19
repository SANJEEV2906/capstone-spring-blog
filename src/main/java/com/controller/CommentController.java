package com.controller;

import com.dto.CommentDto;
import com.entity.Comment;
import com.exception.ResourceNotFoundException;
import com.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blogs")
public class CommentController {

    @Autowired
    private CommentService commentService;
    
    
    //To post a comment on a Id
    @PostMapping("/comment")
    public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto commentDto) throws ResourceNotFoundException {
        CommentDto savedComment = commentService.addComment(commentDto);
        return ResponseEntity.ok(savedComment);
    }
}
