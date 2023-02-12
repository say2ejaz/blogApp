package com.masai.controller;

import com.masai.model.Blog;
import com.masai.model.Comment;
import com.masai.model.User;
import com.masai.service.BlogService;
import com.masai.service.CommentService;
import com.masai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/masaiblog/user")
public class Controllers {

    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<String> demo(){
        return ResponseEntity.ok("hi");
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        userService.registerUser(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @PostMapping("/post/{userId}")
    public ResponseEntity<Blog> publish(@RequestBody Blog blog, @PathVariable int userId){
        blogService.publishBlog(blog, userId);
        return new ResponseEntity<Blog>(blog, HttpStatus.CREATED);
    }

    @PostMapping("/comment/{blogId}/{userId}")
    public ResponseEntity<Comment> writeComment(@RequestBody Comment comment, @PathVariable int blogId, @PathVariable int userId){
        commentService.writeComment(comment, blogId, userId);
        return new ResponseEntity<Comment>(comment, HttpStatus.OK);
    }

    @GetMapping("/blogs")
    public ResponseEntity<List<Blog>> getAllBlogs(){
        return new ResponseEntity<List<Blog>>(userService.getAllBlogs(), HttpStatus.OK);
    }

    @DeleteMapping("/blog/{blogId}/{userId}")
    public ResponseEntity<Boolean> deleteBlog(@PathVariable int blogId, @PathVariable int userId){
        boolean out = userService.deleteBlog(blogId, userId);
        return new ResponseEntity<Boolean>(out, HttpStatus.OK);
    }

    @DeleteMapping("/blog/comment/{commentId}/{userId}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable int commentId, @PathVariable int userId){
        boolean out = userService.deleteComment(commentId, userId);
        return new ResponseEntity<Boolean>(out, HttpStatus.OK);
    }
}
