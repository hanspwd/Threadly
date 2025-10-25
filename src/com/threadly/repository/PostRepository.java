package com.threadly.repository;

import com.threadly.model.Post;
import com.threadly.util.validators.ValidationException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PostRepository {

    private final List<Post> postList = new ArrayList<>();

    public void save(Post post) {
        postList.add(post);
    }

    public List<Post> findAll() {
        return new ArrayList<>(postList);
    }

    public Post findByUUID(String uuid) {
        for(Post post: postList) {
            if(post.getPostUUID().equals(uuid)) {
                return post;
            }
        }
        return null;
    }

    public List<Post> findByAuthorUUID(String authorUUID) {
        List<Post> result = new ArrayList<>();
        for (Post post : postList) {
            if(post.getAuthor().getUserUUID().equals(authorUUID)) {
                result.add(post);
            }
        }
        return result;
    }

    public void deleteByUUID(String uuid) {
        boolean removed = postList.removeIf(post -> post.getPostUUID().equals(uuid));
        if (!removed) {
            throw new ValidationException("No se encontró el post a eliminar.");
        }
    }
}
