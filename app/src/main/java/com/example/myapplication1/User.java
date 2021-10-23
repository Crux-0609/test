package com.example.myapplication1;

public class User {
    private String id;
    private String username;
    private String password;
    private int age;
    private String user;
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }
    public User(String username, String password, int age, String usertype) {
        super();
        this.username = username;
        this.password = password;
        this.age = age;
        this.user = usertype;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getSex() {
        return user;
    }
    public void setSex(String sex) {
        this.user = sex;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password="
                + password + ", age=" + age + ", user=" + user + "]";
    }
}
