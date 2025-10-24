package com.threadly.service;

import com.threadly.model.Comment;
import com.threadly.model.Post;
import com.threadly.model.User;
import com.threadly.repository.CommentRepository;
import com.threadly.util.validators.UserValidator;
import com.threadly.util.validators.ValidationException;

import java.time.LocalDateTime;
import java.util.List;

public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment addComment(User author, Post post, String content) {
        UserValidator.validateIsActive(author);

        if (post == null) {
            throw new ValidationException("El post no existe, no se puede comentar.");
        }

        Comment comment = new Comment(author, post, content);
        commentRepository.save(comment);
        return comment;
    }

    public void editComment(User author, String commentUUID, String newContent) {
        Comment comment = commentRepository.findByUUID(commentUUID);

        if (comment == null) {
            throw new ValidationException("Comentario no encontrado, no se puede editar.");
        }

        if (!comment.getAuthor().getUserUUID().equals(author.getUserUUID())) {
            throw new ValidationException("Solo el autor puede editar este comentario.");
        }

        UserValidator.validateIsActive(author);

        if (newContent != null && !newContent.isBlank()) {
            comment.setContent(newContent);
        }

        comment.setUpdatedAt(LocalDateTime.now());
    }

    public List<Comment> listAllComments() {
        return commentRepository.findAll();
    }

    public List<Comment> listCommentsByPost(String postUUID) {
        return commentRepository.findByPostUUID(postUUID);
    }

    public List<Comment> listCommentsByAuthor(User author) {
        return commentRepository.findByAuthorUUID(author.getUserUUID());
    }

    public Comment getCommentByUUID(String uuid) {
        Comment comment = commentRepository.findByUUID(uuid);
        if (comment == null) {
            throw new ValidationException("Comentario no encontrado.");
        }
        return comment;
    }
}
