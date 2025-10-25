package com.threadly.repository;

import com.threadly.model.Comment;
import com.threadly.util.validators.ValidationException;

import java.util.ArrayList;
import java.util.List;

public class CommentRepository {

    private final List<Comment> commentList = new ArrayList<>();

    public void save(Comment comment) {
        commentList.add(comment);
    }

    public List<Comment> findAll() {
        return new ArrayList<>(commentList);
    }

    public Comment findByUUID(String uuid) {
        for (Comment comment : commentList) {
            if (comment.getCommentUUID().equals(uuid)) {
                return comment;
            }
        }
        return null;
    }

    public List<Comment> findByPostUUID(String postUUID) {
        List<Comment> result = new ArrayList<>();
        for (Comment comment : commentList) {
            if (comment.getPost().getPostUUID().equals(postUUID)) {
                result.add(comment);
            }
        }
        return result;
    }

    public List<Comment> findByAuthorUUID(String authorUUID) {
        List<Comment> result = new ArrayList<>();
        for (Comment comment : commentList) {
            if (comment.getAuthor().getUserUUID().equals(authorUUID)) {
                result.add(comment);
            }
        }
        return result;
    }


    public void deleteByUUID(String uuid) {
        boolean removed = commentList.removeIf(comment -> comment.getCommentUUID().equals(uuid));
        if (!removed) {
            throw new ValidationException("No se encontró el comentario a eliminar.");
        }
    }
}
