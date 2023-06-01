package br.com.mrodrigochaves.bean.BlogBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean
@RequestScoped
public class BlogBean {
    @ManagedProperty("#{blogDao}")
    private BlogDao blogDao;

    private List<User> users;
    private List<Post> posts;

    @PostConstruct
    public void init() {
        users = blogDao.getUsers();
        posts = blogDao.getPosts();
    }


    public void setBlogDao(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Post> getPosts() {
        return posts;
    }
}
