package com.masai.service;

import com.masai.model.Blog;
import com.masai.model.User;
import com.masai.repo.BlogRepo;
import com.masai.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements  BlogService{

    @Autowired
    private BlogRepo blogRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public Blog publishBlog(Blog blog, int userId) {
        User user = userRepo.findById(userId).get();
        List<Blog> blogs = user.getBlogs();
        blogs.add(blog);
        blog.setUser(user);
        userRepo.save(user);
//        blogRepo.save(blog);
        return blog;
    }

    @Override
    public List<Blog> categoryBlogs(String category) {
        return null;
    }
}
