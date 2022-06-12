package com.example.gr11today;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.gr11today.daos.TaskDao;

public abstract class Database extends RoomDatabase {
    public abstract TaskDao taskDao();

    public static Database getDatabase(Context context) {
        Database database;

        synchronized (Database.class) {
            database = Room.databaseBuilder(
                            context,
                            Database.class,
                            "database")
                    .allowMainThreadQueries()
                    .build();
        }
        return database;
    }
}

