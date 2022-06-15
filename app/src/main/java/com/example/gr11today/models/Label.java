package com.example.gr11today.models;

import android.content.Context;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

public class Label {
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private String context;
    private Integer taskid;

    public Label(String context, Integer taskid) {
        this.context = context;
        this.taskid = taskid;
    }

    public Label(String context) {
        this.context = context;
    }
}
