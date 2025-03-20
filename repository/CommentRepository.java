package com.repository;


import com.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  //Marks an interface as a repository component.
public interface CommentRepository extends JpaRepository<Comment, Long> {

	
}
