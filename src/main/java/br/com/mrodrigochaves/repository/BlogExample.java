package br.com.mrodrigochaves.repository;

import br.com.mrodrigochaves.bean.Comment;
import br.com.mrodrigochaves.bean.Post;
import br.com.mrodrigochaves.bean.User;

public class BlogExample {
    public static void main(String[] args) {
        // Criando usuários
        User user1 = new User("user1", "password1");
        User user2 = new User("user2", "password2");

        // Criando posts
        user1.createPost("Post 1", "Conteúdo do post 1");
        user2.createPost("Post 2", "Conteúdo do post 2");

        // Adicionando comentários aos posts
        user2.getPosts().get(0).addComment(user1, "Comentário 1 no post 1");
        user1.getPosts().get(0).addComment(user2, "Comentário 2 no post 1");

        // Exibindo informações
        System.out.println("Usuário: " + user1.getUsername());
        System.out.println("Posts:");
        for (Post post : user1.getPosts()) {
            System.out.println("  Título: " + post.getTitle());
            System.out.println("  Conteúdo: " + post.getContent());
            System.out.println("  Comentários:");
            for (Comment comment : post.getComments()) {
                System.out.println("    Autor: " + comment.getUser().getUsername());
                System.out.println("    Conteúdo: " + comment.getContent());
            }
        }
    }
}
