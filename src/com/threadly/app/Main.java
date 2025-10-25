package com.threadly;

import com.threadly.model.users.Admin;
import com.threadly.model.users.Member;
import com.threadly.model.User;
import com.threadly.model.Post;
import com.threadly.model.Category;
import com.threadly.repository.UserRepository;
import com.threadly.repository.PostRepository;
import com.threadly.repository.CommentRepository;
import com.threadly.service.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Repositorios
        UserRepository userRepository = new UserRepository();
        PostRepository postRepository = new PostRepository();
        CommentRepository commentRepository = new CommentRepository();

        // Servicios
        UserService userService = new UserService(userRepository);
        PostService postService = new PostService(postRepository);
        CommentService commentService = new CommentService(commentRepository);

        // Servicio principal
        ForumService forumService = new ForumService(
                userService,
                postService,
                commentService,
                userRepository,
                postRepository,
                commentRepository
        );

        // SIMULACIÓN EN DURO
        System.out.println("----- Simulación Foro -----");
        System.out.println("Registrando usuarios...");

        Member member1 = forumService.registerMember("Hans", "hans@email.com");
        Member member2 = forumService.registerMember("Juan", "juan@email.com");
        Admin admin = forumService.registerAdmin("Admin", "admin@email.com");

        System.out.println("Usuarios " + member1.getUsername() + ", " + member2.getUsername() + " y " + admin.getUsername() + " registrados.");

        System.out.println();

        System.out.println("Creando posts...");

        Post post1 = forumService.createPost(member1, "Ayuda registro", "Como me puedo registrar", Category.HELP);
        Post post2 = forumService.createPost(member2, "Java arrayList", "Como se usan los arrayList?", Category.JAVA);

        System.out.println("Posts creados.");

        System.out.println();

        System.out.println("Añadiendo comentarios...");

        forumService.addComment(member2, post1, "Gracias por la ayuda!");
        forumService.addComment(member1, post2, "Revisa la documentación oficial de java!");

        System.out.println("Comentarios añadidos.");
        System.out.println();

        System.out.println("Lista de posts por autor:");

        List<Post> allPosts = forumService.listAllPosts();
        for (Post post : allPosts) {
            System.out.println("Post: " + post.getTitle() + " por " + post.getAuthor().getUsername());
        }

        System.out.println();

        System.out.println("Lista de todos los usuarios activos:");
        List<User> activeUsers = forumService.listActiveUsers();
        for (User user : activeUsers) {
            System.out.println("Usuario activo: " + user.getUsername());
        }

        admin.banUser(member1, userService);
        System.out.println("\nDespués de que el admin haya baneado a member1\n");

        System.out.println("Lista de todos los usuarios activos:");
        List<User> activeUsers2 = forumService.listActiveUsers();
        for (User user : activeUsers2) {
            System.out.println("Usuario activo: " + user.getUsername());
        }

        // RESULTADO ESPERANDO, EXCEPCIÓN DE USUARIO DESACTIVADO (BANEADO)
        // member1.comment(post1, "Test", commentService);

    }
}