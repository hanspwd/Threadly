package com.threadly.model.users;

import com.threadly.model.User;
import com.threadly.model.Post;
import com.threadly.model.Comment;
import com.threadly.service.UserService;
import com.threadly.service.PostService;
import com.threadly.service.CommentService;

public class Admin extends User {

    public Admin(String username, String email) {
        super(username, email);
    }

    public void banUser(User targetUser, UserService userService) {
        userService.deactivateAccount(targetUser.getUserUUID());
    }

    public void unbanUser(User targetUser, UserService userService) {
        userService.reactivateAccount(targetUser.getUserUUID());
    }

    public void deletePost(Post post, PostService postService) {
        postService.deletePost(post.getPostUUID());
    }

    public void deleteComment(Comment comment, CommentService commentService) {
        commentService.deleteComment(comment.getCommentUUID());
    }
}
