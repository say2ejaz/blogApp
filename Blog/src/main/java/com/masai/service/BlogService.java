package com.masai.service;

import com.masai.model.Blog;

import java.util.List;

public interface BlogService {
    public Blog publishBlog(Blog blog, int UserId);
    public List<Blog> categoryBlogs(String category);
}
