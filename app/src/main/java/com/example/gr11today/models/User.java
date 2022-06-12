package com.example.gr11today.models;

public class User {
    private Integer id;
    private String username;
    private String password;
    private String validatePassword;


    public User() {
    }

    public User(String username, String password, String validatePassword) {
        this.username = username;
        this.password = password;
        this.validatePassword = validatePassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", validatePassword='" + validatePassword + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getValidatePassword() {
        return validatePassword;
    }

    public void setValidatePassword(String validatePassword) {
        this.validatePassword = validatePassword;
    }
}
