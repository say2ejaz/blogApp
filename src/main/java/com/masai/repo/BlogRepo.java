package com.masai.repo;

import com.masai.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepo extends JpaRepository<Blog, Integer> {
}
