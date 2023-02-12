package com.masai.service;

import com.masai.model.Blog;
import com.masai.model.Comment;
import com.masai.model.User;
import com.masai.repo.BlogRepo;
import com.masai.repo.CommentRepo;
import com.masai.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private BlogRepo blogRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public boolean writeComment(Comment com, int blogId, int userId) {
        User user = userRepo.findById(userId).get();
        Blog blog = blogRepo.findById(blogId).get();
        List<Blog> blogsList = user.getBlogs();
        for(Blog b : blogsList){
            if(b.getId()==blogId){
                return false;
            }
        }
        com.setBlog(blog);
        List<Comment> commentList = blog.getComments();
        commentList.add(com);
        List<Comment> usersComment = user.getComments();
        usersComment.add(com);
        com.setUser(user);
        commentRepo.save(com);
        blogRepo.save(blog);
        userRepo.save(user);
        return true;
    }
}
