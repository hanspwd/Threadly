package com.threadly.service;

import com.threadly.model.users.Admin;
import com.threadly.model.users.Member;
import com.threadly.model.User;
import com.threadly.model.Post;
import com.threadly.model.Comment;
import com.threadly.model.Category;
import com.threadly.repository.UserRepository;
import com.threadly.repository.PostRepository;
import com.threadly.repository.CommentRepository;

import java.util.List;

public class ForumService {

    private final UserService userService;
    private final PostService postService;
    private final CommentService commentService;

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public ForumService(UserService userService, PostService postService,
                        CommentService commentService, UserRepository userRepository,
                        PostRepository postRepository, CommentRepository commentRepository) {
        this.userService = userService;
        this.postService = postService;
        this.commentService = commentService;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    // USER

    public Member registerMember(String username, String email) {
        Member member = new Member(username, email);
        userService.registerUser(member); // mark as active on registration
        return member;
    }

    public Admin registerAdmin(String username, String email) {
        Admin admin = new Admin(username, email);
        userService.registerUser(admin);
        return admin;
    }

    public List<User> listActiveUsers() {
        return userRepository.findActiveUsers();
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByUUID(String uuid) {
        return userRepository.findByUUID(uuid);
    }

    public void deactivateUser(User user) {
        userService.deactivateAccount(user.getUserUUID());
    }

    public void reactivateUser(User user) {
        userService.reactivateAccount(user.getUserUUID());
    }

    // POST
    public Post createPost(Member author, String title, String content, Category category) {
        return postService.createPost(title, content, author, category);
    }

    public void editPost(Member author, String postUUID, String newTitle, String newContent, Category newCategory) {
        postService.editPost(author, postUUID, newTitle, newContent, newCategory);
    }

    public List<Post> listAllPosts() {
        return postService.listAllPost();
    }

    public List<Post> listPostsByAuthor(User author) {
        return postService.listPostsByAuthor(author);
    }

    public void deletePost(Admin admin, Post post) {
        admin.deletePost(post, postService);
    }

    // COMMENTS
    public Comment addComment(Member author, Post post, String content) {
        return commentService.addComment(author, post, content);
    }

    public void editComment(Member author, Comment comment, String newContent) {
        commentService.editComment(author, comment.getCommentUUID(), newContent);
    }

    public void deleteComment(Admin admin, Comment comment) {
        admin.deleteComment(comment, commentService);
    }

}
