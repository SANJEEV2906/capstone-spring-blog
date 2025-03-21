package com.service;

import java.util.List;

import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.dto.CommentDto;
import com.entity.Blog;
import com.entity.Comment;
import com.exception.ResourceNotFoundException;
import com.repository.BlogRepository;
import com.repository.CommentRepository;

@Service //Marks a class as a service component.
public class CommentService {


	private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;

    public CommentService(CommentRepository commentRepository, BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
    }
    
    /**
     * Adds a new comment to a blog post.
     * @param blogId The ID of the blog post.
     * @param commentDto The comment details.
     * @return The created comment.
     * @throws ResourceNotFoundException if the blog is not found.
     */
    public CommentDto addComment(Long blogId, CommentDto commentDto) throws ResourceNotFoundException {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found"));

        Comment comment = new Comment();
        comment.setComment(commentDto.getComment());
        comment.setBlog(blog);

        Comment savedComment = commentRepository.save(comment);
        return mapToDto(savedComment);
    }
    private CommentDto mapToDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setBlogId(comment.getBlog().getId());
        dto.setComment(comment.getComment());
        return dto;
    }
    
    
    /**
     * Retrieves all comments for a given blog post.
     * @param blogId The ID of the blog post.
     * @return A list of comments for the blog.
     * @throws ResourceNotFoundException if the blog is not found.
     */
    public List<CommentDto> getCommentsByBlogId(Long id) throws ResourceNotFoundException {
        if (!commentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Blog not found");
        }

        return commentRepository.findById(id)
                .stream()
                .map(comment -> new CommentDto(comment.getId(), id, comment.getComment()))
                .collect(Collectors.toList());
    }

    /**
     * Deletes a comment by its ID.
     * @param id The comment ID.
     * @return A confirmation message.
     * @throws ResourceNotFoundException if the comment is not found.
     */
    public String deleteComment(Long id) throws ResourceNotFoundException {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        
        commentRepository.delete(comment);
        return "Comment has been deleted";
    }
}

