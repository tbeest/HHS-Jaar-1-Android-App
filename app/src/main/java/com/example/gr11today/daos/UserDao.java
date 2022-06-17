package com.example.gr11today.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gr11today.models.User;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user WHERE id = :id")
    User getById(int id);

    @Query("SELECT * FROM user WHERE name = :username")
    User getByUsername(String username);

/*    @Query("SELECT * FROM task WHERE userId = :userId")
    List<Task> getAllTasksByUserId(int userId);*/

    @Insert
    void registerUser(User user);

    @Insert
    void insert(User user);

    @Update
    void Update(User user);

    @Delete
    void delete(User user);
}
