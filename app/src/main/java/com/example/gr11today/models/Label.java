package com.example.gr11today.models;

import android.content.Context;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.gr11today.Database;

import java.util.List;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = User.class,
                parentColumns = "userId",
                childColumns = "userId")
})
public class Label {
    @PrimaryKey(autoGenerate = true)
    private Integer labelId;

    private String name;

    @ColumnInfo(index = true)
    private Integer userId;

    @Ignore
    private User user;

    public Label() {
    }

    @Override
    public String toString() {
        return name;
    }

    @Ignore
    public Label(String name, Integer labelId, int userId) {
        this.name = name;
        this.labelId = labelId;
        this.userId = userId;
    }

    @Ignore
    public Label(String name, int userId) {
        this.name = name;
        this.userId = userId;
    }

    public static List<Label> getAll(Context context) {
        System.out.println(User.getActiveUser().getUserId());
        return Database.getDatabase(context).labelDao().getAll(User.getActiveUser().getUserId());
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
