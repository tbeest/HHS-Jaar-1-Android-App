package com.example.gr11today.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gr11today.models.Task;

import java.util.List;
import com.example.gr11today.models.User;

@Dao
public interface UserDao {
//    @Query("SELECT * FROM users")
//    List<users> getAll();

    @Query("SELECT * FROM users WHERE id = :id")
    User getById(int id);

    @Insert
        void insert(User user);

        @Update
        void Update(User user);

        @Delete
        void delete(User user);
}
