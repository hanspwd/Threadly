package com.threadly.service;

import com.threadly.model.Category;
import com.threadly.model.Post;
import com.threadly.model.User;
import com.threadly.repository.PostRepository;
import com.threadly.util.validators.UserValidator;
import com.threadly.util.validators.ValidationException;

import java.time.LocalDateTime;
import java.util.List;

public class PostService {

    PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(String title, String content, User author, Category category) {
        UserValidator.validateIsActive(author);

        Post post = new Post(title, content, author, category);

        postRepository.save(post);

        return post;
    }

    public void editPost(User author, String postUUID, String newTitle, String newContent, Category newCategory) {
        Post post = postRepository.findByUUID(postUUID);
        if(post == null) {
            throw new ValidationException("Post no encontrado.");
        }

        if(!post.getAuthor().getUserUUID().equals(author.getUserUUID())) {
            throw new ValidationException("Solo el author puede editar este post.");
        }

        UserValidator.validateIsActive(author);

        if (newTitle != null) post.setTitle(newTitle);
        if (newContent != null) post.setContent(newContent);
        if (newCategory != null) post.setCategory(newCategory);

        post.setUpdatedAt(LocalDateTime.now());
    }

    public List<Post> listAllPost() {
        return postRepository.findAll();
    }

    public List<Post> listPostsByAuthor(User author) {
        return postRepository.findByAuthorUUID(author.getUserUUID());
    }

    public Post getPostByUUID(String uuid) {
        Post post = postRepository.findByUUID(uuid);
        if (post == null) {
            throw new RuntimeException("Post no encontrado.");
        }
        return post;
    }
}
