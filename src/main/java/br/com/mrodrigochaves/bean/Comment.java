package br.com.mrodrigochaves.bean;
public class Comment {
    private User user;
    private String content;

    public Comment(User user, String content) {
        this.user = user;
        this.content = content;
    }

    public Comment(User user2, String content2, Post post) {
		// TODO Auto-generated constructor stub
	}

	public User getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

	public Post getPost() {
		// TODO Auto-generated method stub
		return null;
	}
}
