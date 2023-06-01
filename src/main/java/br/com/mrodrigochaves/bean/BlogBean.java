package br.com.mrodrigochaves.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import br.com.mrodrigochaves.dao.BlogDao;

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
        users = blogDao.getUsers(null);
        posts = blogDao.getPosts(null);
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
