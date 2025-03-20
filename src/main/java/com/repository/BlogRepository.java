package com.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Blog;


@Repository  //Marks an interface as a repository component.
public interface BlogRepository extends JpaRepository<Blog, Long> {
	Optional<Blog> findById(Long id);
	
}
