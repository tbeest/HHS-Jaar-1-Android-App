package com.example.gr11today.models;

import android.content.Context;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.gr11today.Database;

import java.util.List;

@Entity
public class Label {
    @PrimaryKey(autoGenerate = true)
    private Integer labelId;

    private String name;

    public Label() {
    }

    @Override
    public String toString() {
        return name;
    }

    @Ignore
    public Label(String name, Integer labelId) {
        this.name = name;
        this.labelId = labelId;
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
        if (label.getLabelId() > 0) {
            Database.getDatabase(context).labelDao().delete(label);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }
}
