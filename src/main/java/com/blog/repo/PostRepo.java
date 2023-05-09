package com.blog.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	Page<Post> findAllByUser(User user,Pageable pageable);
	Page<Post> findAllByCategory(Category category,Pageable pageable);
}
