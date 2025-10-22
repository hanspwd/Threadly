package com.threadly.model;

import com.threadly.util.validators.PostValidator;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class Post {

    private String postUUID;
    private String title;
    private String content;
    private User author;
    private Category category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Post() {
        this.postUUID = java.util.UUID.randomUUID().toString();
        this.setCreatedAt(LocalDateTime.now());
        PostValidator.uuidValidator(postUUID);
    }

    public void setTitle(String title) {
        PostValidator.titleValidator(title);
        this.title = title;
    }

    public void setContent(String content) {
        PostValidator.contentValidator(content);
        this.content = content;
    }

    public void setAuthor(User author) {
        PostValidator.authorValidator(author);
        this.author = author;
    }

    public void setCategory(Category category) {
        PostValidator.categoryValidator(category);
        this.category = category;
    }

    // No debe cambiar
    private void setCreatedAt(LocalDateTime createdAt) {
        PostValidator.createdAtValidator(createdAt);
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        PostValidator.updatedAtValidator(updatedAt, this.createdAt);
        this.updatedAt = updatedAt;
    }
}
