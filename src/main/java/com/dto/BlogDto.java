package com.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class BlogDto {
    private Long id;

    @NotEmpty(message = "Title cannot be blank")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @NotEmpty(message = "Content cannot be blank")
    @Size(min = 3, max = 200, message = "Content must be between 3 and 200 characters")
    private String content;

    // Getters and Setters
    public Long getId() { 
    	return id; 
    }
    public void setId(Long id) { 
    	this.id = id; 
    }

    public String getTitle() { 
    	return title; 
    }
    public void setTitle(String title) { 
    	this.title = title; 
    }

    public String getContent() { 
    	return content; 
    }
    public void setContent(String content) { 
    	this.content = content; 
    }
}
