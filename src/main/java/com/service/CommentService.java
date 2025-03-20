package com.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.CommentDto;
import com.entity.Blog;
import com.entity.Comment;
import com.exception.ResourceNotFoundException;
import com.repository.BlogRepository;
import com.repository.CommentRepository;

@Service
public class CommentService {

    
    
    private final CommentRepository commentRepository;
    
    public CommentService(CommentRepository commentRepository) {
    	this.commentRepository=commentRepository;
    }
    
    @Autowired
    private BlogRepository blogRepository;

    public CommentDto addComment(Long blogId, CommentDto commentDto) throws ResourceNotFoundException {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found"));

        Comment comment = new Comment();
        comment.setComment(commentDto.getComment());
        comment.setBlog(blog);

        Comment savedComment = commentRepository.save(comment);
        return mapToDto(savedComment);
    }

    // Helper method to map Entity to DTO
    private CommentDto mapToDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setBlogId(comment.getBlog().getId());
        dto.setComment(comment.getComment());
        return dto;
    }
    
    
    
    public List<CommentDto> getCommentsByBlogId(Long id) throws ResourceNotFoundException {
        if (!commentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Blog not found");
        }

        return commentRepository.findById(id)
                .stream()
                .map(comment -> new CommentDto(comment.getId(), id, comment.getComment()))
                .collect(Collectors.toList());
    }

    public String deleteComment(Long id) throws ResourceNotFoundException {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        
        commentRepository.delete(comment);
        return "Comment has been deleted";
    }
}

