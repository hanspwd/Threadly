package com.threadly.model;

import com.threadly.util.validators.CommentValidator;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Comment {

    private String commentUUID;
    private String content;
    private User author;
    private Post post;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Comment() {
        this.commentUUID = java.util.UUID.randomUUID().toString();
        this.setCreatedAt(LocalDateTime.now());
    }

    public void setContent(String content) {
        CommentValidator.contentValidator(content);
        this.content = content;
    }

    public void setAuthor(User author) {
        CommentValidator.authorValidator(author);
        this.author = author;
    }

    public void setPost(Post post) {
        CommentValidator.postValidator(post);
        this.post = post;
    }

    private void setCreatedAt(LocalDateTime createdAt) {
        CommentValidator.createdAtValidator(createdAt);
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        CommentValidator.updatedAtValidator(updatedAt, this.createdAt);
        this.updatedAt = updatedAt;
    }
}
