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
    @Query("SELECT * FROM task WHERE done == :done")
    List<Task> getAll(boolean done);

    @Query("SELECT * FROM task WHERE done == :done AND labelId = :labelId")
    List<Task> getAllLabel(boolean done, int labelId);

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
