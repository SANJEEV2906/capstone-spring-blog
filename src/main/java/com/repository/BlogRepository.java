package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Blog;
import com.entity.Comment;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
	Optional<Blog> findById(Long id);
	
}
