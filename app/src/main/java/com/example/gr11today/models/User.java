package com.example.gr11today.models;

import android.content.Context;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

import com.example.gr11today.Database;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private Integer userId;

    @ColumnInfo(name = "name")
    private String username;

    @ColumnInfo(name = "password")
    private String password;

    @Ignore
    private static User activeUser;

    public User() {
    }

    @Ignore
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static void addUser(User user, Context context) {
        if (user != null) {
            Database.getDatabase(context).userDao().insert(user);
        }
    }

    public static void setActiveUser(User user) {
        activeUser = user;
    }

    public static User getActiveUser() {
        return activeUser;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
}