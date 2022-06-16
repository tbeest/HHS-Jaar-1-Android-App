package com.example.gr11today.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gr11today.models.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Query("SELECT * FROM task WHERE done == 0")
    List<Task> getAllOpen();

    @Query("SELECT * FROM task WHERE done == 1")
    List<Task> getAllClosed();

    @Query("SELECT * FROM task WHERE id = :id")
    Task getById(int id);

/*    @Query("SELECT * FROM task WHERE userID = :userID")
    List<Task> getAllTasksByUserID(int userID);*/

    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);
}
