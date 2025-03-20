package com.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 200)
    private String content;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference  //to prevent from infinite recursion
    private List<Comment> comments;
    
    

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

    public List<Comment> getComments() { 
    	return comments; 
    }
    public void setComments(List<Comment> comments) { 
    	this.comments = comments; 
    }
	public Blog(String title, String content, List<Comment> comments ) {
		super();
		this.title = title;
		this.content = content;
		this.comments = comments;
		
	}
	public Blog() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Blog [id=" + id + ", title=" + title + ", content=" + content + ", comments=" + comments
				+ "]";
	}
	
	
	
	
	
    
    
}
