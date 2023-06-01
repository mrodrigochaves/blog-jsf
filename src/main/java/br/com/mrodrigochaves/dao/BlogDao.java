package br.com.mrodrigochaves.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.com.mrodrigochaves.bean.User;
import br.com.mrodrigochaves.bean.Post;
import br.com.mrodrigochaves.bean.Comment;


public class BlogDao {
    
    public static Connection getConnection(){
        Connection conn = null;

            try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/blog_db", "admin", "abcd1234");
        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }

    public void createUser(User user) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao criar usuário: " + e.getMessage());
        }
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                User user = new User(username, password);
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter usuários: " + e.getMessage());
        }

        return users;
    }

    public void createPost(Post post) {
        String sql = "INSERT INTO posts (title, content, author_username) VALUES (?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getContent());
            statement.setString(3, post.getAuthor().getUsername());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao criar postagem: " + e.getMessage());
        }
    }

    public List<Post> getPosts() {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM posts";

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                String authorUsername = resultSet.getString("author_username");

                User author = findUserByUsername(authorUsername);
                Post post = new Post(title, content, author);
                posts.add(post);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter postagens: " + e.getMessage());
        }

        return posts;
    }

    public void createComment(Comment comment) {
        String sql = "INSERT INTO comments (content, user_username, post_title) VALUES (?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, comment.getContent());
            statement.setString(2, comment.getUser().getUsername());
            statement.setString(3, comment.getPost().getTitle());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao criar comentário: " + e.getMessage());
        }
    }

    public List<Comment> getComments() {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT * FROM comments";

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                String content = resultSet.getString("content");
                String userUsername = resultSet.getString("user_username");
                String postTitle = resultSet.getString("post_title");

                User user = findUserByUsername(userUsername);
                Post post = findPostByTitle(postTitle);
                Comment comment = new Comment(user, content, post);
                comments.add(comment);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter comentários: " + e.getMessage());
        }

        return comments;
    }

   
    private User findUserByUsername(String username) {
        User user = null;
        String sql = "SELECT * FROM users WHERE username = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String password = resultSet.getString("password");
                user = new User(username, password);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário por nome de usuário: " + e.getMessage());
        }

        return user;
    }

    private Post findPostByTitle(String title) {
        Post post = null;
        String sql = "SELECT * FROM posts WHERE title = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String content = resultSet.getString("content");
                String authorUsername = resultSet.getString("author_username");
                User author = findUserByUsername(authorUsername);
                post = new Post(title, content, author);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar postagem por título: " + e.getMessage());
        }

        return post;
    }
}
