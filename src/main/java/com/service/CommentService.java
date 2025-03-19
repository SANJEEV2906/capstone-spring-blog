package com.service;

import com.dto.CommentDto;

import com.entity.Blog;
import com.entity.Comment;
import com.exception.ResourceNotFoundException;
import com.repository.BlogRepository;
import com.repository.CommentRepository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BlogRepository blogRepository;

    public CommentDto addComment(CommentDto commentDto) throws ResourceNotFoundException {
        Blog blog = blogRepository.findById(commentDto.getBlogId())
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found with ID: " + commentDto.getBlogId()));

        Comment comment = new Comment();
        comment.setComment(commentDto.getComment());
        comment.setBlog(blog);
        comment.setCreatedAt(LocalDateTime.now());

        Comment savedComment = commentRepository.save(comment);

        return new CommentDto(savedComment.getId(), savedComment.getBlog().getId(), savedComment.getComment(), savedComment.getCreatedAt());
    }
}

