package br.com.mrodrigochaves;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private List<Post> posts;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.posts = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void createPost(String title, String content) {
        Post post = new Post(title, content, this);
        posts.add(post);
    }
}
