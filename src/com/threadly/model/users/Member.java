package com.threadly.model.users;

import com.threadly.model.User;
import com.threadly.model.Post;
import com.threadly.model.Comment;
import com.threadly.model.Category;
import com.threadly.service.PostService;
import com.threadly.service.CommentService;

public class Member extends User {

    public Member(String username, String email) {
        super(username, email);
    }

    public Post publishPost(String title, String content, Category category, PostService postService) {
        return postService.createPost(title, content, this, category);
    }

    public Comment comment(Post post, String content, CommentService commentService) {
        return commentService.addComment(this, post, content);
    }

}
