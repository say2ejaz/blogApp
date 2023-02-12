package com.masai.service;

import com.masai.model.Comment;

public interface CommentService {
    public boolean writeComment(Comment com, int blogId, int userId);
}
