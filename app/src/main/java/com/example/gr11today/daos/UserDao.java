package com.example.gr11today.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.gr11today.models.User;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user WHERE userId = :id")
    User getById(int id);

    @Query("SELECT * FROM user WHERE name = :username")
    User getByUsername(String username);

    @Insert
    void insert(User user);
}
