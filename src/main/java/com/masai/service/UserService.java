package com.masai.service;

import com.masai.model.Blog;
import com.masai.model.User;

import java.util.List;

public interface UserService {
    public User registerUser(User user);
    public List<Blog> getAllBlogs();
    public boolean deleteBlog(int blogId, int userId);
    public boolean deleteComment(int commentId, int userId);
}
