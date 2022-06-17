package com.example.gr11today.models;

import android.content.Context;
import android.provider.ContactsContract;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

import com.example.gr11today.Converters;
import com.example.gr11today.Database;

import java.util.List;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = "name")
    private String username;

    @ColumnInfo(name = "password")
    private String password;

    @Ignore
    User activeUser;

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
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static void addUser(User user, Context context) {
        if (user != null) {
            Database.getDatabase(context).userDao().insert(user);
        }
    }

    public void setActiveUser(User user) {
        activeUser = user;
    }

    public User getActiveUser() {
        return activeUser;
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
}