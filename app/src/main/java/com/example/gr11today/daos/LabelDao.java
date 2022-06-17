package com.example.gr11today.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gr11today.models.Label;

import java.util.List;

@Dao
public interface LabelDao {
    @Query("SELECT * FROM label WHERE userId = :userId")
    List<Label> getAll(int userId);

    @Query("SELECT * FROM label WHERE labelId = :labelId AND userId = :userId")
    Label getById(int labelId, int userId);

    @Insert
    void insert(Label label);

    @Update
    void update(Label label);

    @Delete
    void delete(Label label);
}
