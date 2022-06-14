package com.example.gr11today.daos;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.gr11today.models.User;

@Dao
public interface UserDao {

    @Insert
    void registerUser(User user);
}
