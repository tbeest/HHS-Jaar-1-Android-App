package com.example.gr11today.models;

import android.content.Context;
import android.provider.ContactsContract;

import androidx.core.graphics.PathParser;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.gr11today.Database;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Label {
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private String name;

    public Label() {
    }

    @Ignore
    public Label(String context, Integer id) {
        this.name = context;
        this.id = id;
    }

    @Ignore
    public Label(String context) {
        this.name = context;
    }

    public static List<Label> getAll(Context context) {
        return Database.getDatabase(context).labelDao().getAll();
    }

    public static void addLabel(Label label, Context context) {
        if (label != null) {
            Database.getDatabase(context).labelDao().insert(label);
        }
    }

    public static void updateLabel(Label label, Context context) {
        if (label != null) {
            Database.getDatabase(context).labelDao().update(label);
        }
    }

    public static void deleteLabel(Label label, Context context) {
        if (label.getId() > 0) {
            Database.getDatabase(context).labelDao().delete(label);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
