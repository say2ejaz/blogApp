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
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BlogRepo blogRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Override
    public User registerUser(User user) {
        userRepo.save(user);
        return user;
    }

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepo.findAll();
    }

    @Override
    public boolean deleteBlog(int blogId, int userId) {
       User user = userRepo.findById(userId).get();
       List<Blog> blogsList = user.getBlogs();
       boolean flag = false;
       for(Blog blog : blogsList){
           if(blog.getId()==blogId){
               flag = true;
           }
       }
       if(flag){
           Blog blog = blogRepo.findById(blogId).get();
           blogsList.remove(blog);
//           blog.setUser(null);


           List<Comment> commentsOnBlog = blog.getComments();
           List<User> allUsers = userRepo.findAll();

           for(User u : allUsers){
               for(Comment c : commentsOnBlog){
                   if(u.getComments().contains(c)){
                       List<Comment> commentListo = u.getComments();
                       commentListo.remove(c);
                       u.setComments(commentListo);
                   }
               }
           }
           userRepo.save(user);

           blog.setComments(null);

           List<Comment> allComments = commentRepo.findAll();
           for(Comment c : allComments){
               if(c.getBlog().getId()==blogId){
                   commentRepo.delete(c);
               }
           }

           blogRepo.delete(blog);
           return true;
       }else{
           return false;
       }
    }

    @Override
    public boolean deleteComment(int commentId, int userId) {
        User user = userRepo.findById(userId).get();
        Comment comment = commentRepo.findById(commentId).get();
        Blog commentOnBlog = comment.getBlog();
        List<Blog> blogList = user.getBlogs();
        boolean flag = false;
        for(Blog b : blogList){
            if(b.getId()==commentOnBlog.getId()){
                flag = true;
            }
        }
        if(flag){
            List<Comment> commentsList = commentOnBlog.getComments();
            commentsList.remove(comment);
            blogRepo.save(commentOnBlog);

            User commentedBy = comment.getUser();
            List<Comment> allCommentsByUser = commentedBy.getComments();
            allCommentsByUser.remove(comment);
            userRepo.save(commentedBy);

//            comment.setUser(null);
//            comment.setBlog(null);

            commentRepo.delete(comment);
            return true;
        }else{
            return false;
        }
    }
}
